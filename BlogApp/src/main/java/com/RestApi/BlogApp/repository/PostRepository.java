package com.RestApi.BlogApp.repository;

import com.RestApi.BlogApp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
