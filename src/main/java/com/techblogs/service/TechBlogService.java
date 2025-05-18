package com.techblogs.service;

import com.techblogs.model.TechBlog;
import java.util.List;
import java.util.Optional;

public interface TechBlogService {
    TechBlog createBlog(TechBlog blog);
    Optional<TechBlog> getBlog(Long id);
    List<TechBlog> getAllBlogs();
    Optional<TechBlog> updateBlog(Long id, TechBlog blog);
    boolean deleteBlog(Long id);
} 