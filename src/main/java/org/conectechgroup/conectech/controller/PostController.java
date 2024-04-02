package org.conectechgroup.conectech.controller;

import org.conectechgroup.conectech.model.Post;
import org.conectechgroup.conectech.model.User;
import org.conectechgroup.conectech.service.PostService;
import org.conectechgroup.conectech.service.UserService;
import org.conectechgroup.conectech.service.util.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @PostMapping("/{id}/posts")
    public ResponseEntity<Void> addPostToUser(@PathVariable Integer id, @RequestBody Post post) {
        User user = userService.findById(id);
        user.getPosts().add(post);
        userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        List<Post> list = postService.findALL();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable Integer id) {
        Post obj = postService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping(value = "/title")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam String title) {
        List<Post> list = postService.findByTitle(title);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value="text", defaultValue="") String text,
            @RequestParam(value="minDate", defaultValue="") String minDate,
            @RequestParam(value="maxDate", defaultValue="") String maxDate) {
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> list = postService.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }
}