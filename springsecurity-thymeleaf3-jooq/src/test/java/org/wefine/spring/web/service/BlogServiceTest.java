package org.wefine.spring.web.service;

import org.junit.Test;
import org.wefine.spring.AbstractSpringTest;
import org.wefine.spring.web.dto.Comment;
import org.wefine.spring.web.dto.Post;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;


public class BlogServiceTest extends AbstractSpringTest {

    @Resource
    private BlogService blogService;

    @Test
    public void findAllPosts() {
        List<Post> posts = blogService.getAllPosts();
        assertNotNull(posts);
        assertTrue(!posts.isEmpty());
        posts.forEach(System.err::println);
    }

    @Test
    public void findPostById() {
        Post post = blogService.getPost(1);
        assertNotNull(post);
        System.out.println(post);
        List<Comment> comments = post.getComments();
        System.out.println(comments);
    }

    @Test
    public void createPost() {
        Post post = new Post(0, "My new Post",
                "This is my new test post",
                new Timestamp(System.currentTimeMillis()));
        Post savedPost = blogService.createPost(post);
        Post newPost = blogService.getPost(savedPost.getId());
        assertEquals("My new Post", newPost.getTitle());
        assertEquals("This is my new test post", newPost.getContent());
    }

    @Test
    public void createComment() throws InterruptedException {
        Integer postId = 3;
        Comment tempComment = null;
        for (int i = 0; i < 100; i++) {
            Comment comment = new Comment(0, postId, "User4",
                    "user4@gmail.com", "This is my new comment on post1",
                    new Timestamp(System.currentTimeMillis()));
            tempComment = blogService.createComment(comment);
            Thread.sleep(500);
        }
        Post post = blogService.getPost(postId);
        List<Comment> comments = post.getComments();
        assertNotNull(comments);

        final Comment savedComment = tempComment;
        comments.stream().filter(comm -> Objects.equals(savedComment.getId(), comm.getId())).forEach(comm -> {
            assertEquals("User4", comm.getName());
            assertEquals("user4@gmail.com", comm.getEmail());
            assertEquals("This is my new comment on post1", comm.getContent());
        });

    }
}
