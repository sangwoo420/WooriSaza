package project.woori_saza.model.domain;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.woori_saza.model.repo.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserProfileTest {

    @Autowired
    UserProfileRepo userProfileRepo;
    @Autowired
    MemberInfoRepo memberInfoRepo; // 삭제
    @Autowired
    CommentRepo commentRepo;
    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    QnaRepo qnaRepo; // 삭제
    @Autowired
    ZzimRepo zzimRepo; // 삭제
    @Autowired
    ArticleRepo articleRepo;

    @Test
    void userTest() {
        UserProfile user = new UserProfile("ssafyid", "seoul", "kim", LocalDateTime.now(), null, null, null, null, null, null, null, null, null, null, null);
        userProfileRepo.save(user);

        UserProfile user2 = new UserProfile("wooriid", "GJ", "Lee", LocalDateTime.now(), null, null, null, null, null, null, null, null, null, null, null);
        userProfileRepo.save(user2);

        Qna qna=new Qna(1,"카테고리","내용","제목","댓글",null,user);
        qnaRepo.save(qna); //저장

        Review review=new Review(1,"리뷰내용", LocalDateTime.now(),130,user,user2);
        reviewRepo.save(review); //저장

        Article article = new Article(1L, "title", "content", LocalDateTime.now(), "link", null, null, null, null, null, user, null);
        articleRepo.save(article);

        Comment comment = new Comment(1L, "content", LocalDateTime.now(), null, user);
        commentRepo.save(comment);

        Zzim zzim=new Zzim(user,article);
        zzimRepo.save(zzim);

        // TODO: 날리면 안되는거 null로 설정해주기~ CascadeType.Remove vs orphanRemoval

        userProfileRepo.deleteById(user.getId());

    }

}