package project.woori_saza.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.CommentDto;
import project.woori_saza.model.service.ArticleService;
import project.woori_saza.model.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comment")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Api("댓글 컨트롤러")
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    ArticleService articleService;

    @ApiOperation(value = "선택한 게시글 내의 댓글", notes = "선택한 게시글 내의 모든 댓글을 반환한다.")
    @GetMapping("/{articleId}")
    public ResponseEntity<List<CommentDto>> GetCommentList(@PathVariable("articleId") @ApiParam(value = "게시글 번호", required = true) Long articleId) {
        List<CommentDto> list = commentService.getCommentList(articleId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "마이사자 - 댓글 (내가 쓴 댓글)", notes = "마이사자의 '댓글' 창에 내가 쓴 모든 댓글을 반환한다.")
    @PostMapping
    public ResponseEntity<List<CommentDto>> GetMyCommentList(@RequestBody
                                                             @ApiParam(value = "내가 쓴 댓글 정보", required = true,
                                                                     example = "{\n" +
                                                                             "\"profileId\" : \"hashwoori\"\n" +
                                                                             "}") Map<String, String> map) {
        List<CommentDto> list = commentService.getMyCommentList(map.get("profileId"));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 작성", notes = "게시글 내에 댓글을 작성한다.")
    @PostMapping("/{articleId}")
    public ResponseEntity<String> InsertComment(@RequestBody @ApiParam(value = "댓글 작성 모델") CommentDto commentDto) {
        commentService.insertComment(commentDto);
        return null;
    }

    @ApiOperation(value = "댓글 수정", notes = "게시글 내의 내가 쓴 댓글을 수정한다.")
    @PutMapping
    public ResponseEntity<String> UpdateComment(@RequestBody @ApiParam(value = "댓글 수정 모델") CommentDto commentDto) {
        commentService.updateComment(commentDto);
        return null;
    }

    @ApiOperation(value = "댓글 삭제", notes = "게시글 내의 내가 쓴 댓글을 삭제한다.")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> DeleteComment(@PathVariable("commentId") @ApiParam(value = "댓글 번호", required = true) Long commentId) {
        HttpStatus status = null;
        try {
            commentService.deleteComment(commentId);
            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            System.out.println("500에러");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(status);
    }

}
