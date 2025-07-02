package com.dat.backend.blogservice.controller;

import com.dat.backend.blogservice.dto.BlogResponse;
import com.dat.backend.blogservice.dto.CreateNewBlog;
import com.dat.backend.blogservice.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogService blogService;

    @PostMapping("/create")
    public ResponseEntity<BlogResponse> createBlog(@RequestBody CreateNewBlog createNewBlog) {
        return ResponseEntity.ok(blogService.createBlog(createNewBlog));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BlogResponse>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogResponse> getBlogById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.deleteBlog(id));
    }

    @PostMapping("/like/{id}/decrease")
    public ResponseEntity<String> likeBlog(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.decreaseLike(id));
    }

    @PostMapping("/like/{id}/increase")
    public ResponseEntity<String> unlikeBlog(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.increaseLike(id));
    }
}
