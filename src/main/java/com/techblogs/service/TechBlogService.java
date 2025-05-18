package com.techblogs.service;

import com.techblogs.model.TechBlog;
import java.util.List;

public interface TechBlogService {
    TechBlog createBlog(TechBlog techBlog);
    TechBlog updateBlog(Long id, TechBlog techBlog);
    TechBlog getBlogById(Long id);
    List<TechBlog> getAllBlogs();
    List<TechBlog> getBlogsByTopic(String topic);
    List<TechBlog> getBlogsByAuthor(String author);
    void deleteBlog(Long id);
} 