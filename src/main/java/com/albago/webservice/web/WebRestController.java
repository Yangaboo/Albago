package com.albago.webservice.web;

import com.albago.webservice.URL;
import com.albago.webservice.domain.Posts;
import com.albago.webservice.dto.posts.CommentsSaveRequestDto;
import com.albago.webservice.dto.posts.PostsSaveRequestDto;
import com.albago.webservice.service.CommentsService;
import com.albago.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@AllArgsConstructor
public class WebRestController {
    private PostsService postsService;
    private CommentsService commentsService;

    @GetMapping("/")
    public String index(){
        return "Main";
    }

    @GetMapping("/posts")
    public Page<Posts> getPosts(@PageableDefault(size = 10, sort = {"postId"}, direction = Sort.Direction.DESC) Pageable pageable, @RequestParam int catId) {
        if (catId == 0) {
            return postsService.findAll(pageable);
        } else {
            return postsService.findPostsByCatId(catId, pageable);
        }
    }

    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
        return postsService.save(dto);
    }

    @PostMapping("/posts/{post_id}/comments")
    public Long saveComments(@RequestBody CommentsSaveRequestDto dto) {
        return commentsService.save(dto);
    }

    @GetMapping("/posts/{post_id}")
    public HashMap<String, ArrayList> getPost(@PathVariable Long post_id) {
        HashMap<String, ArrayList> Post = new HashMap<String, ArrayList>();

        ArrayList post = new ArrayList();
        post.add(postsService.findOne(post_id));
        ArrayList comment = new ArrayList();
        comment.add(commentsService.findComments(post_id));

        Post.put("post", post);
        Post.put("comments", comment);

        return Post;
    }

    @PostMapping("/posts/{post_id}/favor")
    public String likePost(@PathVariable Long post_id) {
        String currentFavor = postsService.findFavor(post_id);
        postsService.updateFavor(Integer.parseInt(currentFavor)+1, post_id);
        return "Success";
    }

    @PostMapping("/posts/{post_id}/hate")
    public String hatePost(@PathVariable Long post_id) {
        String currentHate = postsService.findHate(post_id);
        postsService.updateHate(Integer.parseInt(currentHate)+1, post_id);
        return "Success";
    }

    @PostMapping("/posts/favor/{comment_id}")
    public String likeComments(@PathVariable Long comment_id) {
        String currentFavor = commentsService.findFavor(comment_id);
        commentsService.updateFavor(Integer.parseInt(currentFavor)+1, comment_id);
        return "Success";
    }

    @PostMapping("/posts/hate/{comment_id}")
    public String hateComments(@PathVariable Long comment_id) {
        String currentHate = commentsService.findHate(comment_id);
        commentsService.updateHate(Math.toIntExact(comment_id)+1, comment_id);
        return "Success";
    }

    @DeleteMapping("/posts/{post_id}/delete/{comment_id}")
    public ResponseEntity<?> deleteComments(@PathVariable Long comment_id, @RequestBody URL pwd) {
        try{
            String requestPwd = commentsService.findPwd(comment_id);
            String Pwd = pwd.getPwd();
            if(requestPwd.equals(Pwd)) {
                commentsService.deleteComment(comment_id);
                return new ResponseEntity<String>("success deleted",HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("password incorrect",HttpStatus.FORBIDDEN);
            }
        }
        catch (NullPointerException e) {
            return new ResponseEntity<String>("Already deleted",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/posts/{post_id}/delete")
    public ResponseEntity<?> deletePosts(@PathVariable Long post_id, @RequestBody URL pwd) {
        try{
            String requestPwd = postsService.findPwd(post_id);
            String Pwd = pwd.getPwd();
            if(requestPwd.equals(Pwd)) {
                postsService.deletePost(post_id);
                commentsService.deleteCommentByPostId(post_id);
                return new ResponseEntity<String>("Success deleted",HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("password incorrect",HttpStatus.FORBIDDEN);
            }
        }
        catch (NullPointerException e) {
            return new ResponseEntity<String>("Already deleted",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/filter")
    public HashMap<String, String> filter(@RequestBody URL url) throws IOException {
        return url.Crawling(url);
    }
}