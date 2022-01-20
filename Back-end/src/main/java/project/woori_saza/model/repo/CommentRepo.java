package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
