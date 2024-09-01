package com.example.MyBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.MyBlog.model.Post;
import com.example.MyBlog.service.PostService;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    // Show all posts
    @GetMapping("/posts")
    public String viewPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "view_posts";
    }

    // Show form to create a new post
    @GetMapping("/posts/new")
    public String showNewPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "new_post";
    }

    // Handle form submission to create a new post
    @PostMapping("/posts")
    public String addPost(@ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/posts";
    }

    // Show form to update a post
    @GetMapping("/posts/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Post post = postService.getPostById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        model.addAttribute("post", post);
        return "update_post";
    }

    // Handle form submission to update a post
    @PostMapping("/posts/update/{id}")
    public String updatePost(@PathVariable("id") Long id, @ModelAttribute Post post) {
        postService.updatePost(id, post);
        return "redirect:/posts";
    }

    // Handle request to delete a post
    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
