package com.barisyenigun.blogserver.controller;

import com.barisyenigun.blogserver.request.RateRequest;
import com.barisyenigun.blogserver.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rank")
public class RateController {
    private final RateService rateService;

    @Autowired
    public RateController(RateService rateService){
        this.rateService = rateService;
    }

    @PostMapping
    public void createRank(@RequestBody RateRequest body, @RequestParam Long postId){
        rateService.createRank(body, postId);
    }

    @GetMapping("/average")
    public double getAverageRankOfAPost(@RequestParam Long postId){
        return rateService.getAverageRankOfAPost(postId);
    }

    @PutMapping("/{id}")
    public void updateRank(@RequestBody RateRequest body, @PathVariable Long id){
        rateService.updateRank(body, id);
    }

    @DeleteMapping("/{id}")
    public void deleteRank(@PathVariable Long id){
        rateService.deleteRank(id);
    }
}
