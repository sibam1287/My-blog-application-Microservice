package com.microservice.post.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class PostDto {
    private String postId;
    private String description;
    private String content;
    private String title;

    List<Comment> comments;
}
