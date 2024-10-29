package com.barisyenigun.blogserver.controller;


import com.barisyenigun.blogserver.request.TagRequest;
import com.barisyenigun.blogserver.response.TagResponse;
import com.barisyenigun.blogserver.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService){
        this.tagService = tagService;
    }

    @PostMapping
    public TagResponse createTag(@RequestBody TagRequest body){
        return tagService.createTag(body);
    }

    @GetMapping("/{id}")
    public TagResponse getTag(@PathVariable Long id){
        return tagService.getTag(id);
    }

    @GetMapping
    public List<TagResponse> getTags(){
        return tagService.getTags();
    }
}
