package com.barisyenigun.blogserver.service;

import com.barisyenigun.blogserver.bucket.BucketName;
import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.PasswordsMismatchException;
import com.barisyenigun.blogserver.exception.UserNotFoundException;
import com.barisyenigun.blogserver.repository.UserRepository;
import com.barisyenigun.blogserver.request.user.ChangePasswordRequest;
import com.barisyenigun.blogserver.request.user.UpdateUserRequest;
import com.barisyenigun.blogserver.response.user.UserResponse;
import com.barisyenigun.blogserver.service.filestore.StorageService;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final StorageService storageService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, StorageService storageService, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.storageService = storageService;
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
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        return UserResponse.fromEntity(user);
    }

    public void uploadProfilePhoto(MultipartFile file){
        Optional<User> optionalUser = getAuthenticatedUser();
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (!file.isEmpty()){
                isFile(file);
                Map<String, String> metadata = extractMetadata(file);
                String path = String.format("%s/%s",BucketName.STORAGE_BUCKET.getBucketName(),user.getId());
                String filename = String.format("%s-%s",file.getName(),UUID.randomUUID());
                try {
                    storageService.upload(path,filename,Optional.of(metadata),file.getInputStream());
                    user.setProfilePhotoLink(filename);
                    userRepository.save(user);
                }
                catch (IOException e){
                    throw new IllegalStateException(e);
                }
            }
        }
        else{
            throw new UserNotFoundException();
        }
    }
    public void updateUser(UpdateUserRequest body){

    }

    public void changePassword(ChangePasswordRequest body){
        User user = getAuthenticatedUser().orElseThrow(() -> new UserNotFoundException());
        if (body.getNewPassword().equals(body.getNewPasswordRepeat())){
            user.setPassword(passwordEncoder.encode(body.getNewPassword()));
        }
        else{
            throw new PasswordsMismatchException();
        }
        userRepository.save(user);
    }

    public byte[] downloadProfilePhoto(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        String path = String.format("%s/%s",BucketName.STORAGE_BUCKET.getBucketName(),user.getId());
        
        return null;
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length",String.valueOf(file.getSize()));
        return metadata;
    }

    private void isFile(MultipartFile file) {
        if (!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(),ContentType.IMAGE_PNG.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("Cannot upload file!");
        }
    }


}
