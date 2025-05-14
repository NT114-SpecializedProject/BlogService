package com.dat.backend.blogservice.service;

import com.dat.backend.blogservice.dto.BlogResponse;
import com.dat.backend.blogservice.dto.CreateNewBlog;
import com.dat.backend.blogservice.entity.Blog;
import com.dat.backend.blogservice.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogResponse createBlog(CreateNewBlog createNewBlog) {
        Blog blog = Blog.builder()
                .title(createNewBlog.getTitle())
                .content(createNewBlog.getContent())
                .authorId(createNewBlog.getAuthorId())
                .build();
        blogRepository.save(blog);
        return BlogResponse.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .content(blog.getContent())
                .authorId(blog.getAuthorId())
                .createdAt(blog.getCreated())
                .build();
    }

    public List<BlogResponse> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream()
                .map(blog -> BlogResponse.builder()
                        .id(blog.getId())
                        .title(blog.getTitle())
                        .content(blog.getContent())
                        .authorId(blog.getAuthorId())
                        .createdAt(blog.getCreated())
                        .build())
                .toList();
    }

    public BlogResponse getBlogById(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
        return BlogResponse.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .content(blog.getContent())
                .authorId(blog.getAuthorId())
                .createdAt(blog.getCreated())
                .build();
    }
}
