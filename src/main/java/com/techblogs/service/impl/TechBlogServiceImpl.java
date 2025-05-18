package com.techblogs.service.impl;

import com.techblogs.model.TechBlog;
import com.techblogs.repository.TechBlogRepository;
import com.techblogs.service.TechBlogService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.micrometer.tracing.annotation.NewSpan;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class TechBlogServiceImpl implements TechBlogService {

    private final TechBlogRepository techBlogRepository;

    private final Counter blogCreationCounter;
    private final Counter blogUpdateCounter;
    private final Counter blogDeletionCounter;
    private final Timer blogOperationTimer;

    public TechBlogServiceImpl(TechBlogRepository techBlogRepository, MeterRegistry meterRegistry) {
        this.techBlogRepository = techBlogRepository;
        
        // Initialize counters
        this.blogCreationCounter = Counter.builder("blog.creation.count")
                .description("Number of blog posts created")
                .register(meterRegistry);
        
        this.blogUpdateCounter = Counter.builder("blog.update.count")
                .description("Number of blog posts updated")
                .register(meterRegistry);
        
        this.blogDeletionCounter = Counter.builder("blog.deletion.count")
                .description("Number of blog posts deleted")
                .register(meterRegistry);
        
        this.blogOperationTimer = Timer.builder("blog.operation.time")
                .description("Time taken for blog operations")
                .register(meterRegistry);
    }

    @Override
    @NewSpan("create-blog")
    @Transactional
    public TechBlog createBlog(TechBlog blog) {
        log.info("Creating new blog post: {}", blog.getTitle());
        blogCreationCounter.increment();
        return blogOperationTimer.wrap((Supplier<TechBlog>) () -> techBlogRepository.save(blog)).get();
    }

    @Override
    @NewSpan("get-blog")
    public Optional<TechBlog> getBlog(Long id) {
        log.info("Fetching blog post with id: {}", id);
        return blogOperationTimer.wrap((Supplier<Optional<TechBlog>>) () -> techBlogRepository.findById(id)).get();
    }

    @Override
    @NewSpan("get-all-blogs")
    public List<TechBlog> getAllBlogs() {
        log.info("Fetching all blog posts");
        return blogOperationTimer.wrap((Supplier<List<TechBlog>>) () -> techBlogRepository.findAll()).get();
    }

    @Override
    @NewSpan("update-blog")
    @Transactional
    public Optional<TechBlog> updateBlog(Long id, TechBlog blog) {
        log.info("Updating blog post with id: {}", id);
        return blogOperationTimer.wrap((Supplier<Optional<TechBlog>>) () -> 
            techBlogRepository.findById(id)
                .map(existingBlog -> {
                    existingBlog.setTitle(blog.getTitle());
                    existingBlog.setContent(blog.getContent());
                    existingBlog.setAuthor(blog.getAuthor());
                    existingBlog.setTags(blog.getTags());
                    blogUpdateCounter.increment();
                    return techBlogRepository.save(existingBlog);
                })
        ).get();
    }

    @Override
    @NewSpan("delete-blog")
    @Transactional
    public boolean deleteBlog(Long id) {
        log.info("Deleting blog post with id: {}", id);
        return blogOperationTimer.wrap((Supplier<Boolean>) () -> 
            techBlogRepository.findById(id)
                .map(blog -> {
                    techBlogRepository.delete(blog);
                    blogDeletionCounter.increment();
                    return true;
                })
                .orElse(false)
        ).get();
    }
} 