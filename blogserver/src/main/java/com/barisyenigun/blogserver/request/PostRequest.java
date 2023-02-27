package com.barisyenigun.blogserver.request;

import com.barisyenigun.blogserver.entity.PostType;
import com.barisyenigun.blogserver.entity.Tag;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class PostRequest {
    private String title;
    private String description;
    private Tag tag;
    private PostType type;
    private LocalDate updatedDate = LocalDate.now();
}
