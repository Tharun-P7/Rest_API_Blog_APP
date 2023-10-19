package com.RestApi.BlogApp.service;

import com.RestApi.BlogApp.entity.Comment;
import com.RestApi.BlogApp.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long postId,CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(Long postId);
    CommentDto getCommentsById(Long postId,Long commentId);
    CommentDto updateComment(Long postId,Long commentId,CommentDto commentDto);
    void deleteComment(Long postId,Long commentId);
}
