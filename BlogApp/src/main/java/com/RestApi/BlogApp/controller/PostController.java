package com.RestApi.BlogApp.controller;

import com.RestApi.BlogApp.entity.Post;
import com.RestApi.BlogApp.payload.PostDto;
import com.RestApi.BlogApp.payload.PostResponse;
import com.RestApi.BlogApp.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy
    ){
        return postService.getAllPosts(pageNo,pageSize,sortBy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostByID(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostByID(@RequestBody PostDto postDto,@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.updatePostById(postDto,id));

//        PostDto postResponse = postService.updatePostById(postDto,id);
//        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostByID(@PathVariable(name = "id") Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Successfully Deleted",HttpStatus.OK);
    }
}
