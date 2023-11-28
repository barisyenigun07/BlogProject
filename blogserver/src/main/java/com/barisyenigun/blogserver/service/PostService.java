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
import com.barisyenigun.blogserver.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final FileUtil fileUtil;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService, UserRepository userRepository, FileUtil fileUtil){
        this.postRepository = postRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.fileUtil = fileUtil;
    }

    public void createPost(PostRequest postRequest) {
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));

        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setDescription(postRequest.getDescription());

        if (postRequest.getCaptionPhoto() != null) {
            String captionPhotoUrl = fileUtil.uploadFile(postRequest.getCaptionPhoto(), "image/", "post_caption_photos");
            post.setCaptionPhotoLink(captionPhotoUrl);
        }

        post.setPostType(postRequest.getPostType());
        post.setModifiedDate(postRequest.getUpdatedDate());
        post.setTags(postRequest.getTags());
        post.setUser(user);


        switch (postRequest.getPostType()) {
            case "ARTICLE" -> {
                post.setContent(postRequest.getArticleContent());
            }
            case "VIDEO" -> {
                String videoUrl = fileUtil.uploadFile(postRequest.getMediaContent(), "video/", "videos");
                post.setContent(videoUrl);
            }
            case "PODCAST" -> {
                String podcastUrl = fileUtil.uploadFile(postRequest.getMediaContent(), "audio/", "podcasts");
                post.setContent(podcastUrl);
            }
            default -> System.out.println("Illegal post type!");
        }

        postRepository.save(post);
    }

    public void uploadArticleImage(MultipartFile file) {
        fileUtil.uploadFile(file, "image/", "article_images");
    }

    public PostResponse getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        return PostResponse.fromEntity(post);
    }

    public byte[] getCaptionPhoto(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        return fileUtil.downloadFile("post_caption_photos", post.getCaptionPhotoLink());
    }

    public byte[] getFileContent(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        return fileUtil.downloadFile(post.getPostType().toLowerCase() + "s", post.getContent());
    }

    public Page<PostResponse> getPostsByUserAndPostType(Long userId, String postType, int page, int size){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Pageable pageable = PageRequest.of(page, size, Sort.by("publishedDate").descending());
        Page<Post> postsByUserAndPostType = postRepository.findAllByUserAndPostType(user, postType, pageable);
        return postsByUserAndPostType.map(PostResponse::fromEntity);
    }

    public Page<PostResponse> getPostsByUser(Long userId, int page, int size) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Pageable pageable = PageRequest.of(page, size, Sort.by("publishedDate").descending());
        return null;
    }

    public List<PostResponse> getPostsByTagName(String tagName) {
        List<Post> postsByTag = postRepository.findAll().stream().filter(post -> post.getTags().stream().anyMatch(tag -> tag.getTagName().equals(tagName))).toList();
        return postsByTag.stream().map(PostResponse::fromEntity).collect(Collectors.toList());
    }

    public List<PostResponse> getPosts(){
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostResponse::fromEntity).collect(Collectors.toList());
    }

    public void updatePost(Long id, PostRequest postRequest){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));

        if (!post.getUser().equals(user)) {
            throw new UnauthorizedException();
        }

        post.setTitle(postRequest.getTitle());
        post.setDescription(postRequest.getDescription());

        if (postRequest.getCaptionPhoto() != null) {
            if (post.getCaptionPhotoLink() != null) {
                fileUtil.deleteFile("post_caption_photos", post.getCaptionPhotoLink());
            }

            String captionPhotoUrl = fileUtil.uploadFile(postRequest.getCaptionPhoto(), "image/", "post_caption_photos");
            post.setCaptionPhotoLink(captionPhotoUrl);
        }

        post.setTags(postRequest.getTags());

        switch (post.getPostType()) {
            case "ARTICLE" -> {
                post.setContent(postRequest.getArticleContent());
            }
            case "VIDEO" -> {
                fileUtil.deleteFile("videos", post.getContent());
                String videoUrl = fileUtil.uploadFile(postRequest.getMediaContent(), "video/", "videos");
                post.setContent(videoUrl);
            }
            case "PODCAST" -> {
                fileUtil.deleteFile("podcasts", post.getContent());
                String podcastUrl = fileUtil.uploadFile(postRequest.getMediaContent(), "audio/", "podcasts");
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
            fileUtil.deleteFile("post_caption_photos", post.getCaptionPhotoLink());
        }

        if (post.getPostType().equals("VIDEO") || post.getPostType().equals("PODCAST")) {
            fileUtil.deleteFile((post.getPostType().toLowerCase() + "s"), post.getContent());
        }
        postRepository.deleteById(id);
    }
}
