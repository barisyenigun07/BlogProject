package com.barisyenigun.blogserver.service;

import com.barisyenigun.blogserver.entity.Tag;
import com.barisyenigun.blogserver.exception.ResourceNotFoundException;
import com.barisyenigun.blogserver.exception.ResourceType;
import com.barisyenigun.blogserver.exception.TagExistsException;
import com.barisyenigun.blogserver.repository.TagRepository;
import com.barisyenigun.blogserver.request.TagRequest;
import com.barisyenigun.blogserver.response.TagResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    public TagResponse createTag(TagRequest body){
        if (tagRepository.existsByTagName(body.getTagName())) {
            throw new TagExistsException();
        }

        Tag tag = new Tag();
        tag.setTagName(body.getTagName());
        return TagResponse.fromEntity(tagRepository.save(tag));
    }
    public List<TagResponse> getTags(){
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(TagResponse::fromEntity).collect(Collectors.toList());
    }

    public TagResponse getTag(Long id){
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.TAG));
        return TagResponse.fromEntity(tag);
    }
}
