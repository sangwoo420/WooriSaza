package project.woori_saza;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.*;
import project.woori_saza.model.repo.*;

import javax.annotation.PostConstruct;
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
//            UserAuth userAuth = new UserAuth("ssafyid", false, null);
//            userAuthRepo.save(userAuth);
//
//            UserAuth userAuth2 = new UserAuth("wooriid", false, null);
//            userAuthRepo.save(userAuth2);
//
//            UserProfile user = new UserProfile("hashssafy", "광주광역시 북구 면앙로6번길 48",126.9130945,35.1780739, "kim1", LocalDateTime.now(), null, null, null, null, userAuth, null, null, null, null, new ArrayList<>(), null,null);
//            userProfileRepo.save(user);
//
//            UserProfile user2 = new UserProfile("hashwoori", "서울특별시 종로구 종로1가",0.0,0.0, "Lee1", LocalDateTime.now(), null, null, null, null, userAuth2, null, null, null, null, new ArrayList<>(), null, null);
//            userProfileRepo.save(user2);
//
//            Party party = new Party(1L, false, LocalDateTime.now(), "ss", 10000, 40, 4, 5, false, 50,null, null,null);
//            Party party1db=partyRepo.save(party);
//
//
//            Party party2 = new Party(2L, false, LocalDateTime.now(), "bb", 20000, 20, 4, 5, false,50, null,null, null);
//            Party party2db=partyRepo.save(party2);
//
//            Article article = new Article(1L, "title", "content", LocalDateTime.now(), "link", null, user, party1db, null, null, new ArrayList<>(), new ArrayList<>(),null);
//
//            article = articleRepo.save(article);
//            party.setArticle(article);
//
//            Article article2 = new Article(2L, "title2", "content", LocalDateTime.now(), "link", null, user2, party2db, null, null, new ArrayList<>(), new ArrayList<>(), null);
//
//            article2 = articleRepo.save(article2);
//            party2.setArticle(article2);
//
//            ChatRoom chatRoom = new ChatRoom("room1",article.getTitle(),1,article,null,null);
//            chatRoom = chatRoomRepo.save(chatRoom);
//            article.setChatRoom(chatRoom);
//
//            Qna qna1=new Qna(1L,"카테고리1","내용1","제목1","답변",null,user);
//            qnaRepo.save(qna1); //저장
//
//            Qna qna2=new Qna(2L,"카테고리2","내용2","제목2","답변",null,user);
//            qnaRepo.save(qna2); //저장
//
//            Qna qna3=new Qna(3L,"카테고리3","내용3","제목3","답변",null,user2);
//            qnaRepo.save(qna3); //저장
//
//            Review review1 = new Review(1L, "리뷰1내용", LocalDateTime.now(), 10, user, user2);
//            reviewRepo.save(review1); //저장
//
//            Comment comment = new Comment(1L, "content", LocalDateTime.now(), articleRepo.getById(1L), user);
//            commentRepo.save(comment);
//
//            Comment comment2 = new Comment(2L, "content2", LocalDateTime.now(), articleRepo.getById(1L), user2);
//            commentRepo.save(comment2);
//
//            Comment comment3 = new Comment(3L, "content3", LocalDateTime.now(), articleRepo.getById(1L), user);
//            commentRepo.save(comment3);
//
//            Zzim zzim = new Zzim(user, article);
//            zzimRepo.save(zzim);
//
//            MemberInfo memberInfo = new MemberInfo(null,false,1,2500,"카카오페이",false,false,user,party);
//            memberInfoRepo.save(memberInfo);
//
//            MemberInfo memberInfo2 = new MemberInfo(null,false,1,2500,"카카오페이",false,false,user,party2);
//            memberInfoRepo.save(memberInfo2);

        }

    }


}
