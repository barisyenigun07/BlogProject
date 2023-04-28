package com.barisyenigun.blogserver.service;

import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.PasswordsMismatchException;
import com.barisyenigun.blogserver.exception.ResourceNotFoundException;
import com.barisyenigun.blogserver.exception.ResourceType;
import com.barisyenigun.blogserver.repository.UserRepository;
import com.barisyenigun.blogserver.request.ChangePasswordRequest;
import com.barisyenigun.blogserver.request.UpdateUserRequest;
import com.barisyenigun.blogserver.response.UserResponse;
import com.barisyenigun.blogserver.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final FileUtil fileUtil;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, FileUtil fileUtil, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.fileUtil = fileUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getAuthenticatedUser(){
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.equals("anonymousUser")){
            return Optional.empty();
        }
        return userRepository.findById(Long.parseLong(principal));
    }

    public UserResponse getUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        return UserResponse.fromEntity(user);
    }
    public void updateUser(UpdateUserRequest body){
        User user = getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        user.setName(body.getName());
        user.setUsername(body.getUsername());
        user.setEmail(body.getEmail());

        String captionPhotoLink = fileUtil.uploadFile(body.getCaptionPhoto(), "image/", "user_caption_photos");
        user.setCaptionPhotoLink(captionPhotoLink);

        String profilePhotoLink = fileUtil.uploadFile(body.getProfilePhoto(), "image/", "user_profile_photos");
        user.setProfilePhotoLink(profilePhotoLink);

        userRepository.save(user);
    }

    public void changePassword(ChangePasswordRequest body){
        User user = getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        if (body.getNewPassword().equals(body.getNewPasswordRepeat())){
            user.setPassword(passwordEncoder.encode(body.getNewPassword()));
        }
        else{
            throw new PasswordsMismatchException();
        }
        userRepository.save(user);
    }

    public byte[] getProfilePhoto(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        return fileUtil.downloadFile("user_profile_photos", user.getProfilePhotoLink());
    }

    /*public byte[] downloadProfilePhoto(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        String path = String.format("%s/%s",BucketName.STORAGE_BUCKET.getBucketName(),user.getId());
        return storageService.download(path, user.getProfilePhotoLink());
    }*/
}
