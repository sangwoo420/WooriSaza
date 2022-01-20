package project.woori_saza.model.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.woori_saza.model.repo.UserAuthRepo;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAuthTest {

    @Autowired
    UserAuthRepo userAuthRepo;

    @Test
    void join(){
        UserAuth userAuth = new UserAuth("kimkim", false, null);
        userAuthRepo.save(userAuth);
    }


}