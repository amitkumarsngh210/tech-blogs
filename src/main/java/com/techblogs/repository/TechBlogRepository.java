package com.techblogs.repository;

import com.techblogs.model.TechBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechBlogRepository extends JpaRepository<TechBlog, Long> {
} 