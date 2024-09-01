package com.example.MyBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MyBlog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
