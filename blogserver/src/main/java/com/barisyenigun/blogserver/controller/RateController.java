package com.barisyenigun.blogserver.controller;

import com.barisyenigun.blogserver.request.RateRequest;
import com.barisyenigun.blogserver.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate")
public class RateController {
    private final RateService rateService;

    @Autowired
    public RateController(RateService rateService){
        this.rateService = rateService;
    }

    @PostMapping
    public void createRate(@RequestBody RateRequest body, @RequestParam("post_id") Long postId){
        rateService.createRate(body, postId);
    }

    @PutMapping("/{id}")
    public void updateRate(@RequestBody RateRequest body, @PathVariable Long id){
        rateService.updateRate(body, id);
    }

    @DeleteMapping("/{id}")
    public void deleteRate(@PathVariable Long id){
        rateService.deleteRate(id);
    }
}
