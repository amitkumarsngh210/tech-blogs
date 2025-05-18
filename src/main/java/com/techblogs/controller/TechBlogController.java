package com.techblogs.controller;

import com.techblogs.model.TechBlog;
import com.techblogs.service.TechBlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class TechBlogController {

    private static final Logger logger = LoggerFactory.getLogger(TechBlogController.class);
    private final TechBlogService techBlogService;

    @PostMapping
    public ResponseEntity<TechBlog> createBlog(@Valid @RequestBody TechBlog techBlog) {
        logger.debug("Received request to create new blog post with title: {}", techBlog.getTitle());
        TechBlog createdBlog = techBlogService.createBlog(techBlog);
        logger.info("Successfully created blog post with id: {}", createdBlog.getId());
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechBlog> updateBlog(@PathVariable Long id, @Valid @RequestBody TechBlog techBlog) {
        logger.debug("Received request to update blog post with id: {}", id);
        TechBlog updatedBlog = techBlogService.updateBlog(id, techBlog);
        logger.info("Successfully updated blog post with id: {}", id);
        return ResponseEntity.ok(updatedBlog);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechBlog> getBlogById(@PathVariable Long id) {
        logger.debug("Received request to fetch blog post with id: {}", id);
        TechBlog blog = techBlogService.getBlogById(id);
        logger.info("Successfully retrieved blog post with id: {}", id);
        return ResponseEntity.ok(blog);
    }

    @GetMapping
    public ResponseEntity<List<TechBlog>> getAllBlogs() {
        logger.debug("Received request to fetch all blog posts");
        List<TechBlog> blogs = techBlogService.getAllBlogs();
        logger.info("Successfully retrieved {} blog posts", blogs.size());
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/topic/{topic}")
    public ResponseEntity<List<TechBlog>> getBlogsByTopic(@PathVariable String topic) {
        logger.debug("Received request to fetch blog posts for topic: {}", topic);
        List<TechBlog> blogs = techBlogService.getBlogsByTopic(topic);
        logger.info("Successfully retrieved {} blog posts for topic: {}", blogs.size(), topic);
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<TechBlog>> getBlogsByAuthor(@PathVariable String author) {
        logger.debug("Received request to fetch blog posts by author: {}", author);
        List<TechBlog> blogs = techBlogService.getBlogsByAuthor(author);
        logger.info("Successfully retrieved {} blog posts by author: {}", blogs.size(), author);
        return ResponseEntity.ok(blogs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        logger.debug("Received request to delete blog post with id: {}", id);
        techBlogService.deleteBlog(id);
        logger.info("Successfully deleted blog post with id: {}", id);
        return ResponseEntity.noContent().build();
    }
} 