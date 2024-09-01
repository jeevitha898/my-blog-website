package com.example.MyBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MyBlog.model.Post;
import com.example.MyBlog.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }
    
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public void updatePost(Long id, Post updatedPost) {
        Optional<Post> existingPostOpt = postRepository.findById(id);
        if (existingPostOpt.isPresent()) {
            Post existingPost = existingPostOpt.get();
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setContent(updatedPost.getContent());
            postRepository.save(existingPost);
        }
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

}
