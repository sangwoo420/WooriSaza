package project.woori_saza;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.*;
import project.woori_saza.model.repo.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        @Autowired
        UserAuthRepo userAuthRepo;
        @Autowired
        UserProfileRepo userProfileRepo;
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

        @Autowired
        PartyRepo partyRepo; // 삭제
        @Autowired
        MemberInfoRepo memberInfoRepo; // 삭제
        @Autowired
        PaidFormRepo paidFormRepo; // 삭제

        public void dbInit1() {
            UserAuth userAuth = new UserAuth("ssafyid", false, null);
            userAuthRepo.save(userAuth);

            UserAuth userAuth2 = new UserAuth("wooriid", false, null);
            userAuthRepo.save(userAuth2);

            UserProfile user = new UserProfile("hashssafy", "seoul", "kim", LocalDateTime.now(), null, null, null, null, userAuth, null, null, null, null, null, null);
            userProfileRepo.save(user);

            UserProfile user2 = new UserProfile("hashwoori", "GJ", "Lee", LocalDateTime.now(), null, null, null, null, userAuth2, null, null, null, null, null, null);
            userProfileRepo.save(user2);

            Party party = new Party(1L, false, LocalDateTime.now(), "ss", 10000, 40, 4, 5, false, null, null, null);
            partyRepo.save(party);

            Article article = new Article(null, "title", "content", LocalDateTime.now(), "link", null, user, party, null, null, new ArrayList<>(), new ArrayList<>());

            article = articleRepo.save(article);
//            Article article2 = new Article(2L, "title2", "content2", LocalDateTime.now(), "link", null, user, party, null, null, new ArrayList<>(), new ArrayList<>());
//            articleRepo.save(article2);

            Qna qna1=new Qna(1L,"카테고리1","내용1","제목1","답변",null,user);
            qnaRepo.save(qna1); //저장

            Qna qna2=new Qna(2L,"카테고리2","내용2","제목2","답변",null,user);
            qnaRepo.save(qna2); //저장

            Qna qna3=new Qna(3L,"카테고리3","내용3","제목3","답변",null,user2);
            qnaRepo.save(qna3); //저장

            Review review1 = new Review(1L, "리뷰1내용", LocalDateTime.now(), 100, user, user2);
            reviewRepo.save(review1); //저장

            Comment comment = new Comment(1L, "content", LocalDateTime.now(), articleRepo.getById(1L), user);
            commentRepo.save(comment);

            Comment comment2 = new Comment(2L, "content2", LocalDateTime.now(), articleRepo.getById(1L), user2);
            commentRepo.save(comment2);

            Comment comment3 = new Comment(3L, "content3", LocalDateTime.now(), articleRepo.getById(1L), user);
            commentRepo.save(comment3);

//            Zzim zzim=new Zzim(user,article);
//            zzimRepo.save(zzim);

            MemberInfo memberInfo = new MemberInfo(1L, false, 1, 2500, false, user, party);
            memberInfoRepo.save(memberInfo);

        }

    }


}
