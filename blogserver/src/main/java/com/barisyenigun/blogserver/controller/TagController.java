package com.barisyenigun.blogserver.controller;

import com.barisyenigun.blogserver.entity.Tag;
import com.barisyenigun.blogserver.request.tag.TagRequest;
import com.barisyenigun.blogserver.response.tag.TagResponse;
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
    public void postTag(@RequestBody TagRequest body){
        tagService.postTag(body);
    }

    @GetMapping("/{tagId}")
    public TagResponse getTag(@PathVariable Long tagId){
        return tagService.getTag(tagId);
    }

    @GetMapping
    public List<Tag> getTags(){
        return tagService.getTags();
    }
}
