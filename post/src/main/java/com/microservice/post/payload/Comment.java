package com.microservice.post.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String commentId;
    private String email;
    private String name;
    private String body;
    private String postId;


}
