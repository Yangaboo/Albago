package com.albago.webservice.web;

import com.albago.webservice.JOB;
import com.albago.webservice.domain.Comments;
import com.albago.webservice.domain.Posts;
import com.albago.webservice.dto.posts.CommentsSaveRequestDto;
import com.albago.webservice.dto.posts.PostsSaveRequestDto;
import com.albago.webservice.service.CommentsService;
import com.albago.webservice.service.PostsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javafx.geometry.Pos;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public Comments saveComments(@RequestBody CommentsSaveRequestDto dto) {
        commentsService.save(dto);
        String post_id = String.valueOf(dto.getPost_id());
        List<Comments> comment = commentsService.findComments(Long.valueOf(post_id));
        return comment.get(comment.size()-1);
    }

    @GetMapping("/posts/{post_id}")
    public HashMap<String, ArrayList> getPost(@PathVariable Long post_id) {
        String currentVisit = postsService.findVisit(post_id);
        postsService.updateVisit(Integer.parseInt(currentVisit)+1, post_id);
        //AutoDelete auto = new AutoDelete();
        HashMap<String, ArrayList> Post = new HashMap<String, ArrayList>();

        ArrayList post = new ArrayList();
        post.add(postsService.findOne(post_id));
        ArrayList comment = new ArrayList();

        List<Long> comment_Ids;
        comment_Ids = commentsService.getCommentId(post_id);

        for (Long comment_id : comment_Ids) {
            double favor = commentsService.getFavor(comment_id);
            double hate = commentsService.getHate(comment_id);
            double ratio = (hate/(hate+favor))*100;

            if (ratio >= 70 | favor+hate>=50 ) { commentsService.deleteComment(comment_id); }
        }
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
    public ResponseEntity<?> deleteComments(@PathVariable Long comment_id, @RequestBody JOB pwd) {
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
    public ResponseEntity<?> deletePosts(@PathVariable Long post_id, @RequestBody JOB pwd) {
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

    @ApiOperation(value = "비교 기능")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startx", value = "x좌표", required = true, dataType = "string", paramType = "body", defaultValue = "127.54125"),
            @ApiImplicitParam(name = "startY", value = "Y좌표", required = true ,dataType = "string", paramType = "body", defaultValue = "36.54989"),
            @ApiImplicitParam(name = "url", value = "알바정보", required = true,  dataType = "string", paramType = "body", defaultValue = ""),
    })
    @PostMapping("/filter")
    public HashMap<String, String> filter(@RequestBody JOB url) throws IOException {
        ArrayList geoLocation;
        String totalDistance;

        HashMap<String, String> crawling =  url.Crawling(url);
        geoLocation = url.convertAddress(crawling.get("address"));

        String endX = String.valueOf(geoLocation.get(1));
        String endY = String.valueOf(geoLocation.get(0));

        String startX = url.getStartX();
        String startY = url.getStartY();

        totalDistance = url.findDistance(startX, startY, endX, endY);

        crawling.put("distance", totalDistance);

        return crawling;
    }

    @GetMapping("/search")
    public Page<Posts> search(@PageableDefault(size = 10, sort = {"postId"}, direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(value = "title", required = true) String title) {
        return postsService.findPostsByTitle(title, pageable);
    }
}