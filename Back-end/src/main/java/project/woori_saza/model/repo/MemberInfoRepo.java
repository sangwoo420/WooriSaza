package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.MemberInfo;

public interface MemberInfoRepo extends JpaRepository<MemberInfo,Long> {
}
