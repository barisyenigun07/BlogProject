package com.barisyenigun.blogserver.service;


import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.ResourceNotFoundException;
import com.barisyenigun.blogserver.exception.ResourceType;
import com.barisyenigun.blogserver.exception.UnauthorizedException;
import com.barisyenigun.blogserver.repository.PostRepository;
import com.barisyenigun.blogserver.repository.UserRepository;
import com.barisyenigun.blogserver.request.PostRequest;
import com.barisyenigun.blogserver.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService, UserRepository userRepository, FileStorageService fileStorageService){
        this.postRepository = postRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }

    public void createPost(PostRequest body) {
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));

        Post post = new Post();
        post.setTitle(body.getTitle());
        post.setDescription(body.getDescription());

        if (body.getCaptionPhoto() != null) {
            String captionPhotoUrl = fileStorageService.uploadFile(body.getCaptionPhoto(), "image/", "post_caption_photos");
            post.setCaptionPhotoLink(captionPhotoUrl);
        }

        post.setPostType(body.getPostType());
        post.setModifiedDate(body.getUpdatedDate());
        post.setTags(body.getTags());
        post.setUser(user);

        switch (body.getPostType()) {
            case "ARTICLE" -> {
                post.setContent(body.getArticleContent());
            }
            case "VIDEO" -> {
                String videoUrl = fileStorageService.uploadFile(body.getMediaContent(), "video/", "videos");
                post.setContent(videoUrl);
            }
            case "PODCAST" -> {
                String podcastUrl = fileStorageService.uploadFile(body.getMediaContent(), "audio/", "podcasts");
                post.setContent(podcastUrl);
            }
            default -> System.out.println("Illegal post type!");
        }


        postRepository.save(post);
    }

    public void uploadArticleImage(MultipartFile file) {
        fileStorageService.uploadFile(file, "image/", "article_images");
    }

    public PostResponse getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        return PostResponse.fromEntity(post);
    }

    public byte[] getCaptionPhoto(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        return fileStorageService.downloadFile("post_caption_photos", post.getCaptionPhotoLink());
    }

    public byte[] getFileContent(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        return fileStorageService.downloadFile(post.getPostType().toLowerCase() + "s", post.getContent());
    }

    public List<PostResponse> getPostsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        List<Post> postsByUser = postRepository.findAllByUser(user);
        return postsByUser.stream().map(PostResponse::fromEntity).collect(Collectors.toList());
    }

    public List<PostResponse> getPostsByTagName(String tagName) {
        List<Post> postsByTag = postRepository.findAll().stream().filter(post -> post.getTags().stream().anyMatch(tag -> tag.getTagName().equals(tagName))).toList();
        return postsByTag.stream().map(PostResponse::fromEntity).sorted(Comparator.comparing(PostResponse::getPublishedDate)).collect(Collectors.toList());
    }

    public List<PostResponse> getPosts(){
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostResponse::fromEntity).sorted(Comparator.comparing(PostResponse::getPublishedDate).reversed()).collect(Collectors.toList());
    }

    public void updatePost(Long id, PostRequest body){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));

        if (!post.getUser().equals(user)) {
            throw new UnauthorizedException();
        }

        post.setTitle(body.getTitle());
        post.setDescription(body.getDescription());

        if (body.getCaptionPhoto() != null) {
            if (post.getCaptionPhotoLink() != null) {
                fileStorageService.deleteFile("post_caption_photos", post.getCaptionPhotoLink());
            }

            String captionPhotoUrl = fileStorageService.uploadFile(body.getCaptionPhoto(), "image/", "post_caption_photos");
            post.setCaptionPhotoLink(captionPhotoUrl);
        }

        post.setTags(body.getTags());

        switch (post.getPostType()) {
            case "ARTICLE" -> {
                post.setContent(body.getArticleContent());
            }
            case "VIDEO" -> {
                fileStorageService.deleteFile("videos", post.getContent());
                String videoUrl = fileStorageService.uploadFile(body.getMediaContent(), "video/", "videos");
                post.setContent(videoUrl);
            }
            case "PODCAST" -> {
                fileStorageService.deleteFile("podcasts", post.getContent());
                String podcastUrl = fileStorageService.uploadFile(body.getMediaContent(), "audio/", "podcasts");
                post.setContent(podcastUrl);
            }
            default -> System.out.println("Illegal post type!");
        }
        postRepository.save(post);
    }

    public void deletePost(Long id){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));

        if (!post.getUser().equals(user)){
            throw new UnauthorizedException();
        }

        if (post.getCaptionPhotoLink() != null) {
            fileStorageService.deleteFile("post_caption_photos", post.getCaptionPhotoLink());
        }

        if (post.getPostType().equals("VIDEO") || post.getPostType().equals("PODCAST")) {
            fileStorageService.deleteFile((post.getPostType().toLowerCase() + "s"), post.getContent());
        }
        postRepository.deleteById(id);
    }
}
