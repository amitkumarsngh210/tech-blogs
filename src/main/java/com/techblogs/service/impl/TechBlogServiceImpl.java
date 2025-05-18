package com.techblogs.service.impl;

import com.techblogs.model.TechBlog;
import com.techblogs.repository.TechBlogRepository;
import com.techblogs.service.TechBlogService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TechBlogServiceImpl implements TechBlogService {

    private static final Logger logger = LoggerFactory.getLogger(TechBlogServiceImpl.class);
    private final TechBlogRepository techBlogRepository;

    @Override
    public TechBlog createBlog(TechBlog techBlog) {
        logger.debug("Creating new blog post with title: {}", techBlog.getTitle());
        TechBlog savedBlog = techBlogRepository.save(techBlog);
        logger.info("Successfully created blog post with id: {}", savedBlog.getId());
        return savedBlog;
    }

    @Override
    public TechBlog updateBlog(Long id, TechBlog techBlog) {
        logger.debug("Updating blog post with id: {}", id);
        TechBlog existingBlog = techBlogRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Blog post not found with id: {}", id);
                    return new EntityNotFoundException("Blog not found with id: " + id);
                });
        
        existingBlog.setTitle(techBlog.getTitle());
        existingBlog.setContent(techBlog.getContent());
        existingBlog.setAuthor(techBlog.getAuthor());
        existingBlog.setTopic(techBlog.getTopic());
        
        TechBlog updatedBlog = techBlogRepository.save(existingBlog);
        logger.info("Successfully updated blog post with id: {}", id);
        return updatedBlog;
    }

    @Override
    @Transactional(readOnly = true)
    public TechBlog getBlogById(Long id) {
        logger.debug("Fetching blog post with id: {}", id);
        return techBlogRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Blog post not found with id: {}", id);
                    return new EntityNotFoundException("Blog not found with id: " + id);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<TechBlog> getAllBlogs() {
        logger.debug("Fetching all blog posts");
        List<TechBlog> blogs = techBlogRepository.findAll();
        logger.info("Retrieved {} blog posts", blogs.size());
        return blogs;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TechBlog> getBlogsByTopic(String topic) {
        logger.debug("Fetching blog posts for topic: {}", topic);
        List<TechBlog> blogs = techBlogRepository.findByTopic(topic);
        logger.info("Retrieved {} blog posts for topic: {}", blogs.size(), topic);
        return blogs;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TechBlog> getBlogsByAuthor(String author) {
        logger.debug("Fetching blog posts by author: {}", author);
        List<TechBlog> blogs = techBlogRepository.findByAuthor(author);
        logger.info("Retrieved {} blog posts by author: {}", blogs.size(), author);
        return blogs;
    }

    @Override
    public void deleteBlog(Long id) {
        logger.debug("Attempting to delete blog post with id: {}", id);
        if (!techBlogRepository.existsById(id)) {
            logger.error("Blog post not found with id: {}", id);
            throw new EntityNotFoundException("Blog not found with id: " + id);
        }
        techBlogRepository.deleteById(id);
        logger.info("Successfully deleted blog post with id: {}", id);
    }
} 