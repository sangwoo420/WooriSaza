package project.woori_saza;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.*;
import project.woori_saza.model.repo.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
class DBTest {

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

    @Test
    void userTest() {
        UserAuth userAuth = new UserAuth("ssafyid",false,null);
        userAuthRepo.save(userAuth);

        UserAuth userAuth2 = new UserAuth("wooriid",false,null);
        userAuthRepo.save(userAuth2);

        UserProfile user = new UserProfile("hashssafy", "seoul", .0,.0, "kim", LocalDateTime.now(), null, null, null, null, userAuth, null, null, null, null, null, null);
        userProfileRepo.save(user);

        UserProfile user2 = new UserProfile("hashwoori", "GJ",.0,.0, "Lee", LocalDateTime.now(), null, null, null, null, userAuth2, null, null, null, null, null, null);
        userProfileRepo.save(user2);

        Party party = new Party(1L, false, LocalDateTime.now(), "ss", 10000, 40, 4, 5, false, 50,null, null,null);
        partyRepo.save(party);

        Article article = new Article(1L, "title", "content", LocalDateTime.now(), "link", null, user, party, null, null, null, null);
        articleRepo.save(article);

        Article article2 = new Article(2L, "title2", "content2", LocalDateTime.now(), "link", null, user, party, null, null, null, null);
        articleRepo.save(article2);

        Qna qna=new Qna(1L,"카테고리","내용","제목","댓글",null,user);
        qnaRepo.save(qna); //저장

        Review review=new Review(1L,"리뷰내용", LocalDateTime.now(),130,user,user2);
        reviewRepo.save(review); //저장

        Comment comment = new Comment(1L, "content", LocalDateTime.now(), article, user);
        commentRepo.save(comment);

        Zzim zzim=new Zzim(user,article);
        zzimRepo.save(zzim);

        MemberInfo memberInfo = new MemberInfo(1L,false,1,2500,"카카오페이",false,false,user,party);
        memberInfoRepo.save(memberInfo);

        // OK TODO: userProfile 삭제시 cascade (memberInfo, review, qna, userauth, zzim)
        // TODO: userProfile 삭제시 날리면 안되는거 null로 설정해주기 (comment, article)
        // 위 두개는 한번에 해줘야함 (참조하고 있기때문)

        /* * 회원 탈퇴 버튼 누름
         * * profile에서 userAuth 찾음
         * * profile에서 관련 연관 끊기
         * * userAuth 지우기 * */

        /* 1 articleRepo에서 프로필을 외래키로 갖는 리스트 불러오기
         * * 2 리스트를 순회하면서 프로필 외래키를 null로 만들기
         */

//        for (Article article1 : articleRepo.findByUserProfile(user)) {
//            article1.setUserProfile(null);
//            articleRepo.save(article1);
//        }
//
//        for(Comment c : commentRepo.findByUserProfile(user)){
//            c.setUserProfile(null);
//            commentRepo.save(c);
//        }
//
//        System.out.println(articleRepo.findByUserProfile(user));
////        System.out.println("articleList = " + articleList);
//
//        userAuthRepo.deleteById(userAuth.getId());


        // OK TODO: comment, zzim 삭제
//        articleRepo.deleteById(1L);

    }

    @Test
    void partyTest() {
        UserProfile user = new UserProfile("ssafyid", "seoul",0.0,0.0, "kim", LocalDateTime.now(), null, null, null, null, null, null, null, null, null, null, null);
        userProfileRepo.save(user);

        UserProfile user2 = new UserProfile("wooriid", "GJ",0.0,0.0, "Lee", LocalDateTime.now(), null, null, null, null, null, null, null, null, null, null, null);
        userProfileRepo.save(user2);

        Party party = new Party(1L, false, LocalDateTime.now(), "ss", 10000, 40, 4, 5, false, 50,null, null,null);
        partyRepo.save(party);

        Article article = new Article(1L, "title", "content", LocalDateTime.now(), "link", null, user, party, null, null, null, null);
        articleRepo.save(article);

        Comment comment = new Comment(1L, "content", LocalDateTime.now(), article, user);
        commentRepo.save(comment);

        Zzim zzim=new Zzim(user,article);
        zzimRepo.save(zzim);

        PaidForm paidForm = new PaidForm(1L,null,1, LocalDate.now(), LocalDate.now(), party);
        paidFormRepo.save(paidForm);

        MemberInfo memberInfo = new MemberInfo(1L,false,1,2500,"카카오페이",false,false,user,party);
        memberInfoRepo.save(memberInfo);

        // OK TODO: 파티 삭제 -> (article -> comment, zzim), paid_form, member_info
        //  partyRepo.deleteById(1L);

    }

}