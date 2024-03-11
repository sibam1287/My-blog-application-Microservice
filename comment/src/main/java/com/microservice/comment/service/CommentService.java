package com.microservice.comment.service;

import com.microservice.comment.config.RestTemplateConfig;
import com.microservice.comment.entity.Comment;
import com.microservice.comment.payload.Post;
import com.microservice.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
@Service
public class CommentService {
       @Autowired
       private RestTemplateConfig restTemplate;
       @Autowired
       private CommentRepository commentReposiotry;



        public Comment saveComment(Comment comment ){
            Post post = restTemplate.getRestTemplate().getForObject("http://POST-SERVICE/api/posts/" + comment.getPostId(), Post.class);

           if(post!=null){
               String commentId = UUID.randomUUID().toString();
               comment.setCommentId(commentId);
               Comment saveComment = commentReposiotry.save(comment);
               return saveComment;
           }else{
               return null;
           }

        }

    public List<Comment> getAllCommentsByPostId(String postId) {

        List<Comment> comments = commentReposiotry.findByPostId(postId);
        return comments;
    }
}