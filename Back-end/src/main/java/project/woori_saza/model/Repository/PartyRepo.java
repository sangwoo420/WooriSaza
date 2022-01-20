package project.woori_saza.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.woori_saza.model.domain.Party;


@Repository
public interface PartyRepo extends JpaRepository<Party, Long> {

}
