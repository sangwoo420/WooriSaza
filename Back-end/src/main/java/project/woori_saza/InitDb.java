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
            UserAuth userAuth = new UserAuth("ssafyid",false,null);
            userAuthRepo.save(userAuth);

            UserAuth userAuth2 = new UserAuth("wooriid",false,null);
            userAuthRepo.save(userAuth2);

            UserProfile user = new UserProfile("hashssafy", "seoul", "kim", LocalDateTime.now(), null, null, null, null, userAuth, null, null, null, null, null, null);
            userProfileRepo.save(user);

            UserProfile user2 = new UserProfile("hashwoori", "GJ", "Lee", LocalDateTime.now(), null, null, null, null, userAuth2, null, null, null, null, null, null);
            userProfileRepo.save(user2);

            Party party = new Party(1L,false, LocalDateTime.now(),"ss",10000,40,4,5,false,null, null,null);
            partyRepo.save(party);

            Article article = new Article(1L, "title", "content", LocalDateTime.now(), "link", null, user, party, null, null, null, null);
            articleRepo.save(article);


            Qna qna=new Qna(1,"카테고리","내용","제목","댓글",null,user);
            qnaRepo.save(qna); //저장

            Review review=new Review(1,"리뷰내용", LocalDateTime.now(),130,user,user2);
            reviewRepo.save(review); //저장

            Comment comment = new Comment(1L, "content", LocalDateTime.now(), article, user);
            commentRepo.save(comment);

            Zzim zzim=new Zzim(user,article);
            zzimRepo.save(zzim);

            MemberInfo memberInfo = new MemberInfo(1L,false,1,2500,false,user,party);
            memberInfoRepo.save(memberInfo);

        }

    }


}
