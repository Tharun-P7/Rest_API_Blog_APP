package com.RestApi.BlogApp.service.impl;

import com.RestApi.BlogApp.entity.Post;
import com.RestApi.BlogApp.exception.ResourceNotFoundException;
import com.RestApi.BlogApp.payload.PostDto;
import com.RestApi.BlogApp.payload.PostResponse;
import com.RestApi.BlogApp.repository.PostRepository;
import com.RestApi.BlogApp.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);
        PostDto postResponse = maptoDto(newPost);
        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo,int pageSize, String sortBy,String sortDir) {
        //Create Pageable instance
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);

        //getContent for page objects
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listofposts = posts.getContent();
        List<PostDto> content= listofposts.stream().map(post -> maptoDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        return maptoDto(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        Post newPost = postRepository.save(post);
        return maptoDto(newPost);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
    }

    private PostDto maptoDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;
    }

    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;
    }
}
