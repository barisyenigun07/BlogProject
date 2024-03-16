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
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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

    public void createRate(RateRequest body, Long postId){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        Rate rate = new Rate();
        rate.setRateLevel(body.getRateLevel());
        rate.setPost(post);
        rate.setUser(user);
        rateRepository.save(rate);
    }

    public void deleteRate(Long id){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Rate rate = rateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.RANK));
        if (!rate.getUser().equals(user)){
            throw new UnauthorizedException();
        }
        rateRepository.deleteById(id);
    }

    public void updateRate(RateRequest body, Long id){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Rate rate = rateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.RANK));

        if (!rate.getUser().equals(user)){
            throw new UnauthorizedException();
        }

        rate.setRateLevel(body.getRateLevel());
        rateRepository.save(rate);
    }
}
