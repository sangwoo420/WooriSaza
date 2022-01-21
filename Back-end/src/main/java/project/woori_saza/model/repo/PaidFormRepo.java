package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.PaidForm;

public interface PaidFormRepo extends JpaRepository<PaidForm, Long> {
}
