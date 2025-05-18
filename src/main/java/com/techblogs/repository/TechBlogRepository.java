package com.techblogs.repository;

import com.techblogs.model.TechBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechBlogRepository extends JpaRepository<TechBlog, Long> {
    List<TechBlog> findByTopic(String topic);
    List<TechBlog> findByAuthor(String author);
} 