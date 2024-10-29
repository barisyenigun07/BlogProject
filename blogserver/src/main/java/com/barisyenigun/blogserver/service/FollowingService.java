package com.barisyenigun.blogserver.service;


import com.barisyenigun.blogserver.entity.Following;
import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.ResourceNotFoundException;
import com.barisyenigun.blogserver.exception.ResourceType;
import com.barisyenigun.blogserver.repository.FollowingRepository;
import com.barisyenigun.blogserver.repository.UserRepository;
import com.barisyenigun.blogserver.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        optionalFollowerFollowed.ifPresent(followingRepository::delete);

    }

    public List<UserResponse> getAllFollowedUsersOfAUser(Long followerUserId){
        User followerUser = userRepository.findById(followerUserId).orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        List<Following> allFollowingsByUser = followingRepository.findAllByFollower(followerUser);
        List<User> allFollowedUsers = new ArrayList<>();

        for (Following following : allFollowingsByUser) {
            allFollowedUsers.add(following.getFollowed());
        }

        return allFollowedUsers.stream().map(user -> UserResponse.fromEntity(user)).collect(Collectors.toList());
    }

}
