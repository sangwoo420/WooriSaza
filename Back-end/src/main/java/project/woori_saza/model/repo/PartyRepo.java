package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.MemberInfo;
import project.woori_saza.model.domain.Party;
import project.woori_saza.model.domain.UserProfile;

import java.util.List;

public interface PartyRepo extends JpaRepository<Party, Long> {

    Party findAllByMemberInfos(MemberInfo memberInfo); //memberInfo에 해당하는 파티 찾기

}
