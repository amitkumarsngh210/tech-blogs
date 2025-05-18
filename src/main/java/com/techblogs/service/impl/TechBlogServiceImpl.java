package com.techblogs.service.impl;

import com.techblogs.model.TechBlog;
import com.techblogs.repository.TechBlogRepository;
import com.techblogs.service.TechBlogService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TechBlogServiceImpl implements TechBlogService {

    private final TechBlogRepository techBlogRepository;

    @Override
    public TechBlog createBlog(TechBlog techBlog) {
        return techBlogRepository.save(techBlog);
    }

    @Override
    public TechBlog updateBlog(Long id, TechBlog techBlog) {
        TechBlog existingBlog = techBlogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found with id: " + id));
        
        existingBlog.setTitle(techBlog.getTitle());
        existingBlog.setContent(techBlog.getContent());
        existingBlog.setAuthor(techBlog.getAuthor());
        existingBlog.setTopic(techBlog.getTopic());
        
        return techBlogRepository.save(existingBlog);
    }

    @Override
    @Transactional(readOnly = true)
    public TechBlog getBlogById(Long id) {
        return techBlogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TechBlog> getAllBlogs() {
        return techBlogRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TechBlog> getBlogsByTopic(String topic) {
        return techBlogRepository.findByTopic(topic);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TechBlog> getBlogsByAuthor(String author) {
        return techBlogRepository.findByAuthor(author);
    }

    @Override
    public void deleteBlog(Long id) {
        if (!techBlogRepository.existsById(id)) {
            throw new EntityNotFoundException("Blog not found with id: " + id);
        }
        techBlogRepository.deleteById(id);
    }
} 