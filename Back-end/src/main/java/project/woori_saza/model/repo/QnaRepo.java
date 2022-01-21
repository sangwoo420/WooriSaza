package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.Qna;

public interface QnaRepo extends JpaRepository<Qna,Integer> {

}
