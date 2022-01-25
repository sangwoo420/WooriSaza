package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Comment;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.CommentDto;
import project.woori_saza.model.repo.ArticleRepo;
import project.woori_saza.model.repo.CommentRepo;
import project.woori_saza.model.repo.UserProfileRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepo commentRepo;
    @Autowired
    ArticleRepo articleRepo;
    @Autowired
    UserProfileRepo userProfileRepo;

    /**
     * 파티 내 댓글 리스트 조회
     */
    @Override
    public List<CommentDto> getCommentList(Long articleId) {
        System.out.println("===파티 댓글 리스트===");
        Article article = articleRepo.getById(articleId);
        List<Comment> comments = commentRepo.findByArticle(article);
        return comments.stream().map(CommentDto::new).collect(Collectors.toList());
    }

    /**
     * 내가 쓴 댓글 리스트 조회
     */
    @Override
    public List<CommentDto> getMyCommentList(String profileId) {
        System.out.println("===파티 댓글 리스트===");
        UserProfile user = userProfileRepo.getById(profileId);
        List<Comment> comments = commentRepo.findByUserProfile(user);
        return comments.stream().map(CommentDto::new).collect(Collectors.toList());
    }

    /**
     * 댓글 작성
     */
    @Override
    public void insertComment(CommentDto commentDto) {
        System.out.println("===댓글 작성===");
        Article article = articleRepo.getById(commentDto.getArticleId());
        UserProfile user = userProfileRepo.getById(commentDto.getProfileId());
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setContent(commentDto.getContent());
        comment.setCreateAt(LocalDateTime.now());
        comment.setArticle(article);
        comment.setUserProfile(user);
        commentRepo.save(comment);
    }

    /**
     * 댓글 수정
     */
    @Override
    public void updateComment(CommentDto commentDto) {
        System.out.println("===댓글 수정===");
        Comment comment = commentRepo.getById(commentDto.getId());
        comment.setContent(commentDto.getContent());
        comment.setCreateAt(LocalDateTime.now());
        commentRepo.save(comment);
    }

    /**
     * 댓글 삭제
     */
    @Override
    public void deleteComment(Long commentId) {
        System.out.println("===댓글 삭제===");
        commentRepo.deleteById(commentId);
    }
}
