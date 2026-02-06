package com.iot.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iot.dto.Result;
import com.iot.entity.Comment;
import com.iot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public Result<Comment> addComment(@RequestBody CommentRequest request,
                                      HttpServletRequest httpRequest) {
        Long userId = getCurrentUserId(httpRequest);
        return (Result<Comment>) commentService.addComment(
                request.getVideoId(),
                userId,
                request.getContent(),
                request.getReplyId()
        );
    }

    @DeleteMapping("/delete/{commentId}")
    public Result<Boolean> deleteComment(@PathVariable Long commentId,
                                         HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        return (Result<Boolean>) commentService.deleteComment(commentId, userId);
    }

    @GetMapping("/video/{videoId}")
    public Result<Page<Comment>> getVideoComments(@PathVariable Long videoId,
                                                  @RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "20") Integer size) {
        return (Result<Page<Comment>>) commentService.getVideoComments(videoId, page, size);
    }

    @GetMapping("/replies/{commentId}")
    public Result<List<Comment>> getCommentReplies(@PathVariable Long commentId) {
        return commentService.getCommentReplies(commentId);
    }

    @GetMapping("/user")
    public Result<Page<Comment>> getUserComments(@RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "20") Integer size,
                                                 HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        return commentService.getUserComments(userId, page, size);
    }

    // 请求参数类
    public static class CommentRequest {
        private Long videoId;
        private String content;
        private Long replyId;

        // getters and setters
        public Long getVideoId() { return videoId; }
        public void setVideoId(Long videoId) { this.videoId = videoId; }

        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }

        public Long getReplyId() { return replyId; }
        public void setReplyId(Long replyId) { this.replyId = replyId; }
    }

    private Long getCurrentUserId(HttpServletRequest request) {
        Object userId = request.getAttribute("userId");
        return userId != null ? Long.valueOf(userId.toString()) : 1L;
    }
}
