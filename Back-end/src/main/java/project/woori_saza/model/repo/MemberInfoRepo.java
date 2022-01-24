package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.MemberInfo;
import project.woori_saza.model.domain.UserAuth;
import project.woori_saza.model.domain.UserProfile;

import java.util.List;

public interface MemberInfoRepo extends JpaRepository<MemberInfo,Long> {

    List<MemberInfo> findAllByUserProfile(UserProfile userProfile); //userprofile아이디로 찾기

}
