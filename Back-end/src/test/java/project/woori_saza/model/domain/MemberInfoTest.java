package project.woori_saza.model.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.woori_saza.model.repo.MemberInfoRepo;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberInfoTest {

    @Autowired
    MemberInfoRepo memberInfoRepo;

    @Test
    void join(){
        MemberInfo member = new MemberInfo(1L, false, 1, 1000, false, null, null);
        memberInfoRepo.save(member);
    }

}