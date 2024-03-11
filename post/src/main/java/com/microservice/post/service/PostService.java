package com.microservice.post.service;

import com.microservice.post.config.RestTemplateConfig;
import com.microservice.post.entity.Post;
import com.microservice.post.payload.PostDto;
import com.microservice.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private RestTemplateConfig restTemplate;
    @Autowired
    private PostRepository postRepository;
    
    public Post savePost(Post post){
        String postId = UUID.randomUUID().toString();
        post.setId(postId);
        Post savedPost = postRepository.save(post);
        return savedPost;
    }

    public Post findPostById(String postId) {
       Post post= postRepository.findById(postId).get();
       return post;
    }

    public PostDto getPostWithComments(String postId) {            //return list thats why we wrote ArrayList.class
        Post post = postRepository.findById(postId).get();
        ArrayList comment= restTemplate.getRestTemplate().getForObject("http://COMMENT-SERVICE/api/comments/"+postId, ArrayList.class);
         PostDto postDto= new PostDto();
          postDto.setPostId(post.getId());
          postDto.setTitle(post.getTitle());
          postDto.setDescription(post.getDescription());
          postDto.setContent(post.getContent());
          postDto.setComments(comment);
          return postDto;

    }
}
