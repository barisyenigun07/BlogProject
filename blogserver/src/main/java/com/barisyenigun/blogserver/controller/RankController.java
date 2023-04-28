package com.barisyenigun.blogserver.controller;

import com.barisyenigun.blogserver.request.RankRequest;
import com.barisyenigun.blogserver.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rank")
public class RankController {
    private final RankService rankService;

    @Autowired
    public RankController(RankService rankService){
        this.rankService = rankService;
    }

    @PostMapping
    public void createRank(@RequestBody RankRequest body, @RequestParam Long postId){
        rankService.createRank(body, postId);
    }

    @GetMapping("/average")
    public double getAverageRankOfAPost(@RequestParam Long postId){
        return rankService.getAverageRankOfAPost(postId);
    }

    @PutMapping("/{id}")
    public void updateRank(@RequestBody RankRequest body, @PathVariable Long id){
        rankService.updateRank(body, id);
    }

    @DeleteMapping("/{id}")
    public void deleteRank(@PathVariable Long id){
        rankService.deleteRank(id);
    }
}
