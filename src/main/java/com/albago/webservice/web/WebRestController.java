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
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

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
    public String deleteComments(@PathVariable Long comment_id, @RequestBody String pwd) {
        if(commentsService.findPwd(comment_id).equals(pwd)) {
            commentsService.deleteComment(comment_id);
        }
        return "success deleted";
    }

    @DeleteMapping("/posts/{post_id}/delete")
    public String deletePosts(@PathVariable Long post_id, @RequestBody URL pwd) {
        String requestPwd = postsService.findPwd(post_id);
        String Pwd = pwd.getPwd();
        if(requestPwd.equals(Pwd)) {
            postsService.deletePost(post_id);
            commentsService.deleteCommentByPostId(post_id);
            return "success deleted";
        } else {
            return "failed";
        }
    }

    @PostMapping("/filter")
    public HashMap<String, String> filter(@RequestBody URL url) throws IOException {
        HashMap<String, String> res = new HashMap<String, String>();
        String uri;
        uri = url.getUrl();
        Connection.Response response = Jsoup.connect(uri)
                .method(Connection.Method.GET)
                .execute();
        Document albaDocument = response.parse();
        Element address = albaDocument.select("li[class=address divide]").select("strong").first();
        Element title = albaDocument.select("p[class=detailTitle]").first();
        Element workTime = albaDocument.select("li[class=worktimecd]").first();
        Element period = albaDocument.select("li[class=workperiodcd]").select("a").first();
        Element pay = albaDocument.select("p[class=pay]").first();
        Element workDate = albaDocument.select("li[class=workweekcd]").select("a").first();
        Element age = albaDocument.select("li[class=agetemp]").first();

        String addressValue = address.text();
        String titleValue = title.text();
        String workTimeValue = workTime.text();
        String periodValue = period.text();
        String payValue = pay.text();
        String workDateValue = workDate.text();
        String ageValue = age.text();

        res.put("name", titleValue);
        res.put("workTime", workTimeValue);
        res.put("period", periodValue);
        res.put("hourlyWage", payValue);
        res.put("days", workDateValue);
        res.put("age", ageValue);

        return res;
    }
}