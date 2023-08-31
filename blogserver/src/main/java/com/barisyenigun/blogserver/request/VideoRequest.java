package com.barisyenigun.blogserver.request;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class VideoRequest extends PostRequest{
    private MultipartFile content;
}
