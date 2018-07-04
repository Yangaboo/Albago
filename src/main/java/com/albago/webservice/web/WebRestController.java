package com.albago.webservice.web;

import com.albago.webservice.domain.CommentsRepository;
import com.albago.webservice.dto.posts.CommentsSaveRequestDto;
import com.albago.webservice.dto.posts.PostsSaveRequestDto;
import com.albago.webservice.service.CommentsService;
import com.albago.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

@RestController
@AllArgsConstructor
public class WebRestController {
    private PostsService postsService;
    private CommentsService commentsService;

    @GetMapping("/")
    public void hello() {
        return;
    }

    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
        return postsService.save(dto);
    }

    @PostMapping("/posts/comments")
    public Long saveComments(@RequestBody CommentsSaveRequestDto dto) {
        return commentsService.save(dto);
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "main";
    }
}