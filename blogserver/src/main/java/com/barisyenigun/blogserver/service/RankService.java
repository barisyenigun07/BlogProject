package com.barisyenigun.blogserver.service;

import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.Rank;
import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.ResourceNotFoundException;
import com.barisyenigun.blogserver.exception.ResourceType;
import com.barisyenigun.blogserver.exception.UnauthorizedException;
import com.barisyenigun.blogserver.repository.PostRepository;
import com.barisyenigun.blogserver.repository.RankRepository;
import com.barisyenigun.blogserver.request.RankRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankService {
    private final RankRepository rankRepository;
    private final PostRepository postRepository;
    private final UserService userService;

    @Autowired
    public RankService(RankRepository rankRepository, PostRepository postRepository, UserService userService){
        this.rankRepository = rankRepository;
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public void createRank(RankRequest body, Long postId){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        Rank rank = new Rank();
        rank.setRankLevel(body.getRankLevel());
        rank.setPost(post);
        post.setUser(user);
        rankRepository.save(rank);
    }

    public double getAverageRankOfAPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        return rankRepository.findAverageRank(post);
    }

    public void deleteRank(Long id){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Rank rank = rankRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.RANK));
        if (!rank.getUser().equals(user)){
            throw new UnauthorizedException();
        }
        rankRepository.deleteById(id);
    }

    public void updateRank(RankRequest body, Long id){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Rank rank = rankRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.RANK));
        if (!rank.getUser().equals(user)){
            throw new UnauthorizedException();
        }

        rank.setRankLevel(body.getRankLevel());
        rankRepository.save(rank);
    }
}
