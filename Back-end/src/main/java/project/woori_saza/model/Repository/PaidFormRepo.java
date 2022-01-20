package project.woori_saza.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.woori_saza.model.domain.PaidForm;

@Repository
public interface PaidFormRepo extends JpaRepository<PaidForm, Long> {
}
