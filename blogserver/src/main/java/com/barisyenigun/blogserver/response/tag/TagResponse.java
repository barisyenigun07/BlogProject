package com.barisyenigun.blogserver.response;

import com.barisyenigun.blogserver.entity.Tag;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class TagResponse {
    private Long id;
    private String tagName;

    public static TagResponse fromEntity(Tag tag){
        if (tag == null){
            return null;
        }
        else {
            return TagResponse.builder()
                    .id(tag.getId())
                    .tagName(tag.getTagName())
                    .build();
        }
    }
}
