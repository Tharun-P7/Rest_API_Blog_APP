package com.RestApi.BlogApp.controller;

import com.RestApi.BlogApp.payload.CommentDto;
import com.RestApi.BlogApp.service.CommentService;
import com.RestApi.BlogApp.service.impl.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") Long postId,@RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto),HttpStatus.CREATED);
    }


    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(name = "postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "postId") Long postId, @PathVariable(name = "commentId") Long commentId){
        CommentDto commentDto = commentService.getCommentsById(postId, commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(name = "postId") Long postId,@PathVariable(value = "commentId") Long commentId,@RequestBody CommentDto updatedComment){
        CommentDto commentDto = commentService.updateComment(postId,commentId,updatedComment);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "postId") Long postId,@PathVariable(name = "commentId") Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted succesfully",HttpStatus.OK);
    }
}
