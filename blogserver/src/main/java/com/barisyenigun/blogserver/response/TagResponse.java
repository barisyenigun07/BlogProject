package com.barisyenigun.blogserver.response;

import com.barisyenigun.blogserver.entity.Tag;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class TagResponse {
    private Long id;
    private String tagName;

    public static TagResponse fromEntity(Tag tag){
        return TagResponse.builder()
                    .id(tag.getId())
                    .tagName(tag.getTagName())
                    .build();
    }
}
