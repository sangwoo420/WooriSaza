package project.woori_saza.model.service;

import project.woori_saza.model.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getCommentList(Long articleId);
    List<CommentDto> getMyCommentList(String profileId);
    void insertComment(CommentDto commentDto);
    void updateComment(CommentDto commentDto);
    void deleteComment(Long commentId);

}