package com.techblogs.controller;

import com.techblogs.model.TechBlog;
import com.techblogs.service.TechBlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class TechBlogController {

    private final TechBlogService techBlogService;

    @PostMapping
    public ResponseEntity<TechBlog> createBlog(@Valid @RequestBody TechBlog techBlog) {
        return new ResponseEntity<>(techBlogService.createBlog(techBlog), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechBlog> updateBlog(@PathVariable Long id, @Valid @RequestBody TechBlog techBlog) {
        return ResponseEntity.ok(techBlogService.updateBlog(id, techBlog));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechBlog> getBlogById(@PathVariable Long id) {
        return ResponseEntity.ok(techBlogService.getBlogById(id));
    }

    @GetMapping
    public ResponseEntity<List<TechBlog>> getAllBlogs() {
        return ResponseEntity.ok(techBlogService.getAllBlogs());
    }

    @GetMapping("/topic/{topic}")
    public ResponseEntity<List<TechBlog>> getBlogsByTopic(@PathVariable String topic) {
        return ResponseEntity.ok(techBlogService.getBlogsByTopic(topic));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<TechBlog>> getBlogsByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(techBlogService.getBlogsByAuthor(author));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        techBlogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
} 