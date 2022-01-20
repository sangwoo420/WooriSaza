package project.woori_saza.model.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.woori_saza.model.repo.UserProfileRepo;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserProfileTest {

    @Autowired
    UserProfileRepo userProfileRepo;

    @Test
    void join(){
        UserProfile user = new UserProfile("ssafyid","seoul","kim",LocalDateTime.now(),null,null,null,null,null,null,null,null,null,null,null);
        userProfileRepo.save(user);

        System.out.println(userProfileRepo.getById("ssafyid").getAddress());
    }

    @Test
    void delete(){
        UserProfile user = new UserProfile("ssafyid","seoul","kim",LocalDateTime.now(),null,null,null,null,null,null,null,null,null,null,null);
        userProfileRepo.save(user);

        userProfileRepo.delete(user);

    }

}