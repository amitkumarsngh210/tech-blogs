package com.techblogs.controller;

import com.techblogs.model.TechBlog;
import com.techblogs.service.TechBlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class TechBlogController {

    private final TechBlogService techBlogService;

    @PostMapping
    public ResponseEntity<TechBlog> createBlog(@RequestBody TechBlog blog) {
        log.info("Received request to create new blog post");
        return ResponseEntity.ok(techBlogService.createBlog(blog));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechBlog> getBlog(@PathVariable Long id) {
        log.info("Received request to get blog post with id: {}", id);
        return techBlogService.getBlog(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TechBlog>> getAllBlogs() {
        log.info("Received request to get all blog posts");
        return ResponseEntity.ok(techBlogService.getAllBlogs());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechBlog> updateBlog(@PathVariable Long id, @RequestBody TechBlog blog) {
        log.info("Received request to update blog post with id: {}", id);
        return techBlogService.updateBlog(id, blog)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        log.info("Received request to delete blog post with id: {}", id);
        return techBlogService.deleteBlog(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
} 