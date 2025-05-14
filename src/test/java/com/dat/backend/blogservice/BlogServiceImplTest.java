package com.dat.backend.blogservice;

import com.dat.backend.blogservice.dto.BlogResponse;
import com.dat.backend.blogservice.dto.CreateNewBlog;
import com.dat.backend.blogservice.entity.Blog;
import com.dat.backend.blogservice.repository.BlogRepository;
import com.dat.backend.blogservice.service.BlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class BlogServiceImplTest {
    @Mock
    private BlogRepository blogRepository; // Mock repository

    private BlogService blogService; // Lớp cần test

    @BeforeEach
    void setUp() {
        blogService = new BlogService(blogRepository); // Khởi tạo service với mock repository
    }

    @Test
    void testCreateBlog() {
        // Dữ liệu đầu vào
        CreateNewBlog createNewBlog = CreateNewBlog.builder()
                .title("Test Blog Title")
                .content("This is a test content.")
                .authorId(1L)
                .build();

        // Blog object giả lập sẽ được trả về từ repository
        Blog mockBlog = new Blog();
        mockBlog.setId(1L);
        mockBlog.setTitle(createNewBlog.getTitle());
        mockBlog.setContent(createNewBlog.getContent());
        mockBlog.setAuthorId(createNewBlog.getAuthorId());

        // Chỉ định hành vi của mock repository (khi save sẽ trả về mockBlog)
        Mockito.when(blogRepository.save(Mockito.any(Blog.class))).thenReturn(mockBlog);

        // Gọi method createBlog và kiểm tra kết quả
        BlogResponse response = blogService.createBlog(createNewBlog);

        // Kiểm tra các giá trị trả về
        assertNotNull(response);  // Kiểm tra xem response có null không
        assertEquals("Test Blog Title", response.getTitle());  // Kiểm tra title
        assertEquals("This is a test content.", response.getContent());  // Kiểm tra content
        assertEquals(1L, response.getAuthorId());  // Kiểm tra authorId
    }
}
