package com.albago.webservice.web;

import com.albago.webservice.URL;
import com.albago.webservice.dto.posts.CommentsSaveRequestDto;
import com.albago.webservice.dto.posts.PostsSaveRequestDto;
import com.albago.webservice.service.CommentsService;
import com.albago.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
@AllArgsConstructor
public class WebRestController {
    private PostsService postsService;
    private CommentsService commentsService;

    @GetMapping("/")
    void hello() {
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

    @GetMapping("/posts/{post_id}")
    public String getPosts(Model model) {
        return "main";
    }

    @DeleteMapping("/posts/{post_id}/delete")
    public Long deletePosts(@PathVariable Long post_id) {
        postsService.delete(post_id);
        return post_id;
    }

    @PostMapping("/filter")
    public HashMap<String, String> filter(@RequestBody URL url) throws IOException {
        HashMap<String, String> res = new HashMap<String, String>();
//        JSONObject json = new JSONObject(res);
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