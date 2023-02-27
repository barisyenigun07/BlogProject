package com.barisyenigun.blogserver.controller;

import com.barisyenigun.blogserver.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post/rank")
public class RankController {
    private final RankService rankService;

    @Autowired
    public RankController(RankService rankService){
        this.rankService = rankService;
    }
}
