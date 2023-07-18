package com.barisyenigun.blogserver.service;

import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.Rate;
import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.ResourceNotFoundException;
import com.barisyenigun.blogserver.exception.ResourceType;
import com.barisyenigun.blogserver.exception.UnauthorizedException;
import com.barisyenigun.blogserver.repository.PostRepository;
import com.barisyenigun.blogserver.repository.RateRepository;
import com.barisyenigun.blogserver.request.RateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateService {
    private final RateRepository rateRepository;
    private final PostRepository postRepository;
    private final UserService userService;

    @Autowired
    public RateService(RateRepository rateRepository, PostRepository postRepository, UserService userService){
        this.rateRepository = rateRepository;
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public void createRank(RateRequest body, Long postId){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        Rate rate = new Rate();
        rate.setRateLevel(body.getRateLevel());
        rate.setPost(post);
        post.setUser(user);
        rateRepository.save(rate);
    }

    public double getAverageRankOfAPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        return rateRepository.findAverageRank(post);
    }

    public void deleteRank(Long id){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Rate rate = rateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.RANK));
        if (!rate.getUser().equals(user)){
            throw new UnauthorizedException();
        }
        rateRepository.deleteById(id);
    }

    public void updateRank(RateRequest body, Long id){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Rate rate = rateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.RANK));
        if (!rate.getUser().equals(user)){
            throw new UnauthorizedException();
        }

        rate.setRateLevel(body.getRateLevel());
        rateRepository.save(rate);
    }
}
