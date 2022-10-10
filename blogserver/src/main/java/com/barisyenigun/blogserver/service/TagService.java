package com.barisyenigun.blogserver.service;

import com.barisyenigun.blogserver.entity.Tag;
import com.barisyenigun.blogserver.exception.TagNotFoundException;
import com.barisyenigun.blogserver.repository.TagRepository;
import com.barisyenigun.blogserver.request.tag.TagRequest;
import com.barisyenigun.blogserver.response.tag.TagResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    public void postTag(TagRequest body){
        Tag tag = new Tag();
        tag.setTagName(body.getTagName());
        tagRepository.save(tag);
    }
    public List<Tag> getTags(){
        List<TagResponse> tags = new ArrayList<>();
        return tagRepository.findAll();
    }

    public TagResponse getTag(Long id){
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException());
        return TagResponse.fromEntity(tag);
    }
}
