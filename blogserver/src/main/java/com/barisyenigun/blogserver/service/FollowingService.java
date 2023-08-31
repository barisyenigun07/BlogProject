package com.barisyenigun.blogserver.service;


import com.barisyenigun.blogserver.entity.Following;
import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.ResourceNotFoundException;
import com.barisyenigun.blogserver.exception.ResourceType;
import com.barisyenigun.blogserver.repository.FollowingRepository;
import com.barisyenigun.blogserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowingService {
    private final FollowingRepository followingRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public FollowingService(FollowingRepository followingRepository, UserRepository userRepository, UserService userService) {
        this.followingRepository = followingRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void followUser(Long followedUserId){
        User followerUser = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        User followedUser = userRepository.findById(followedUserId).orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));

        Following following = new Following();
        following.setFollower(followerUser);
        following.setFollowed(followedUser);

        followingRepository.save(following);
    }

    public void unfollowUser(Long unfollowedUserId) {
        User unfollowerUser = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        User unfollowedUser = userRepository.findById(unfollowedUserId).orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));

        Optional<Following> optionalFollowerFollowed = followingRepository.findByFollowerAndFollowed(unfollowerUser, unfollowedUser);

        if (optionalFollowerFollowed.isPresent()) {
            followingRepository.delete(optionalFollowerFollowed.get());
        }
    }

    public List<User> getAllFollowedUsersOfAUser(Long followerUserId){
        return null;
        //TODO: Logic düzenlenecek
    }

}
