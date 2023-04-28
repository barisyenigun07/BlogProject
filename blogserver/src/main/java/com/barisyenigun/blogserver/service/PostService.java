package com.barisyenigun.blogserver.service;


import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.ResourceNotFoundException;
import com.barisyenigun.blogserver.exception.ResourceType;
import com.barisyenigun.blogserver.exception.UnauthorizedException;
import com.barisyenigun.blogserver.repository.PostRepository;
import com.barisyenigun.blogserver.request.ArticleRequest;
import com.barisyenigun.blogserver.request.PodcastRequest;
import com.barisyenigun.blogserver.request.VideoRequest;
import com.barisyenigun.blogserver.response.PostResponse;
import com.barisyenigun.blogserver.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final FileUtil fileUtil;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService, FileUtil fileUtil){
        this.postRepository = postRepository;
        this.userService = userService;
        this.fileUtil = fileUtil;
    }

    public void createArticle(ArticleRequest body){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Post post = new Post();
        post.setTitle(body.getTitle());
        post.setDescription(body.getDescription());

        String captionPhotoUrl = fileUtil.uploadFile(body.getCaptionPhoto(), "image/", "caption_photos");
        post.setCaptionPhotoLink(captionPhotoUrl);

        post.setContent(body.getContent());
        post.setPostType(String.valueOf(body.getPostType()));
        post.setModifiedDate(body.getUpdatedDate());
        post.setTag(body.getTag());
        post.setUser(user);
        postRepository.save(post);
    }

    /*public void uploadArticleImage(MultipartFile file) {
        FileUtil.isProperType(file, "image/");
        Map<String, String> metadata = FileUtil.extractMetadata(file);
        String path = String.format("%s/image", BucketName.STORAGE_BUCKET.getBucketName());
        String filename = String.format("%s-%s", UUID.randomUUID(), file.getOriginalFilename());
        try {
            storageService.upload(path, filename, Optional.of(metadata), file.getInputStream());
        }
        catch (IOException e){
            throw new FileUploadException();
        }
    }*/

    public void createVideo(VideoRequest body){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Post post = new Post();
        post.setTitle(body.getTitle());
        post.setDescription(body.getDescription());

        String captionPhotoUrl = fileUtil.uploadFile(body.getCaptionPhoto(), "image/", "caption_photos");
        post.setCaptionPhotoLink(captionPhotoUrl);

        String contentUrl = fileUtil.uploadFile(body.getContent(), "video/", "videos");
        post.setContent(contentUrl);

        post.setPostType(String.valueOf(body.getPostType()));
        post.setModifiedDate(body.getUpdatedDate());
        post.setTag(body.getTag());
        post.setUser(user);
        postRepository.save(post);

    }
    public void createPodcast(PodcastRequest body){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Post post = new Post();
        post.setTitle(body.getTitle());
        post.setDescription(body.getDescription());

        String captionPhotoUrl = fileUtil.uploadFile(body.getCaptionPhoto(), "image/", "post_caption_photos");
        post.setCaptionPhotoLink(captionPhotoUrl);

        String contentUrl = fileUtil.uploadFile(body.getContent(), "audio/", "podcasts");
        post.setContent(contentUrl);

        post.setPostType(String.valueOf(body.getPostType()));
        post.setModifiedDate(body.getUpdatedDate());
        post.setTag(body.getTag());
        post.setUser(user);
        postRepository.save(post);
    }

    public PostResponse getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        return PostResponse.fromEntity(post);
    }

    public byte[] getFileContent(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        return fileUtil.downloadFile(post.getPostType().toLowerCase() + "s", post.getContent());
    }

    public byte[] getCaptionPhoto(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        return fileUtil.downloadFile("post_caption_photos", post.getCaptionPhotoLink());
    }

    public List<PostResponse> getPosts(){
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> PostResponse.fromEntity(post)).collect(Collectors.toList());
    }

    public void deletePost(Long id){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        if (!post.getUser().equals(user)){
            throw new UnauthorizedException();
        }
        fileUtil.deleteFile("post_caption_photos", post.getCaptionPhotoLink());
        if (post.getPostType().equals("VIDEO") || post.getPostType().equals("PODCAST")) {
            fileUtil.deleteFile((post.getPostType().toLowerCase() + "s"), post.getContent());
        }
        postRepository.deleteById(id);
    }
}
