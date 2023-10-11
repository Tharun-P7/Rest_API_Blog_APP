package com.RestApi.BlogApp.service;

import com.RestApi.BlogApp.entity.Post;
import com.RestApi.BlogApp.payload.PostDto;
import com.RestApi.BlogApp.payload.PostResponse;

import java.util.List;


public interface PostService {

    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize,String sortBys);
    PostDto getPostById(Long id);
    PostDto updatePostById(PostDto postDto,Long id);
    void deletePostById(Long id);
}
