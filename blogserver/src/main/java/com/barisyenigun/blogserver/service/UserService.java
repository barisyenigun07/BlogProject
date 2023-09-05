package com.barisyenigun.blogserver.service;

import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.ResourceNotFoundException;
import com.barisyenigun.blogserver.exception.ResourceType;
import com.barisyenigun.blogserver.repository.UserRepository;
import com.barisyenigun.blogserver.request.UpdateUserRequest;
import com.barisyenigun.blogserver.response.UserResponse;
import com.barisyenigun.blogserver.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final FileUtil fileUtil;
    @Autowired
    public UserService(UserRepository userRepository, FileUtil fileUtil){
        this.userRepository = userRepository;
        this.fileUtil = fileUtil;
    }

    public Optional<User> getAuthenticatedUser(){
        String username = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            username = authentication.getName();
        }
        return userRepository.findByUsername(username);
    }

    public Long getAuthenticatedUserId(){
        User user = getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        return user.getId();
    }

    public UserResponse getUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        return UserResponse.fromEntity(user);
    }
    public byte[] getProfilePhoto(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        return fileUtil.downloadFile("user_profile_photos", user.getProfilePhotoLink());
    }

    public byte[] getCaptionPhoto(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        return fileUtil.downloadFile("user_caption_photos", user.getProfilePhotoLink());
    }

    public void updateUser(UpdateUserRequest body){
        User user = getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        user.setName(body.getName());
        user.setUsername(body.getUsername());
        user.setEmail(body.getEmail());

        if (body.getCaptionPhoto() != null) {
            if (user.getCaptionPhotoLink() != null) {
                fileUtil.deleteFile("user_caption_photos", user.getCaptionPhotoLink());
            }

            String captionPhotoLink = fileUtil.uploadFile(body.getCaptionPhoto(), "image/", "user_caption_photos");
            user.setCaptionPhotoLink(captionPhotoLink);
        }

        if (body.getProfilePhoto() != null) {
            if (user.getProfilePhotoLink() != null) {
                fileUtil.deleteFile("user_profile_photos", user.getProfilePhotoLink());
            }

            String profilePhotoLink = fileUtil.uploadFile(body.getProfilePhoto(), "image/", "user_profile_photos");
            user.setProfilePhotoLink(profilePhotoLink);
        }


        userRepository.save(user);
    }

    public void deleteUser() {
        User user = getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        userRepository.delete(user);
    }
}
