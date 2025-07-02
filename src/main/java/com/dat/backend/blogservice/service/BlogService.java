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
                .likeCount(0) // Initialize like count to 0
                .build();
        blogRepository.save(blog);
        return BlogResponse.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .content(blog.getContent())
                .authorId(blog.getAuthorId())
                .createdAt(blog.getCreated())
                .likeCount(blog.getLikeCount())
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
                        .likeCount(blog.getLikeCount())
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
                .likeCount(blog.getLikeCount())
                .createdAt(blog.getCreated())
                .build();
    }

    public String deleteBlog(Long id) {
        if (!blogRepository.existsById(id)) {
            throw new RuntimeException("Blog not found");
        }
        blogRepository.deleteById(id);
        return "Blog deleted successfully";
    }

    public String decreaseLike(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
        if (blog.getLikeCount() > 0) {
            blog.setLikeCount(blog.getLikeCount() - 1);
            blogRepository.save(blog);
            return "Successfully";
        }

        return "Fail";
    }

    public String increaseLike(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
        blog.setLikeCount(blog.getLikeCount() + 1);
        blogRepository.save(blog);
        return "Successfully";
    }
}
