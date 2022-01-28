package project.woori_saza.model.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Comment;
import project.woori_saza.model.domain.UserAuth;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.UserProfileDto;
import project.woori_saza.model.repo.ArticleRepo;
import project.woori_saza.model.repo.CommentRepo;
import project.woori_saza.model.repo.UserAuthRepo;
import project.woori_saza.model.repo.UserProfileRepo;
import project.woori_saza.util.HashEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserAuthRepo userAuthRepo;

    @Autowired
    UserProfileRepo userProfileRepo;

    @Autowired
    HashEncoder hashEncoder;

    @Autowired
    ArticleRepo articleRepo;

    @Autowired
    CommentRepo commentRepo;

    @Override
    public UserProfileDto login(String userAuthId) {
        UserAuth userAuth = userAuthRepo.getById(hashEncoder.encode(userAuthId));
        System.out.println(userAuthId + " : " +userAuth.getId());
        UserProfile userProfile= userProfileRepo.findByUserAuth(userAuth);
        return userProfile == null ? null : new UserProfileDto(userProfile);
    }

    @Override
    public UserProfileDto register(UserProfileDto userProfileDto) {

        UserAuth userAuth = new UserAuth(hashEncoder.encode(userProfileDto.getId()), false, null);
        userAuth = userAuthRepo.save(userAuth);

        UserProfile userProfile = new UserProfile(userProfileDto);
        userProfile.setId(hashEncoder.encode(userAuth.getId())); // double hashed id
        userProfile.setUserAuth(userAuth);
        userProfile.setJoinDate(LocalDateTime.now());
        userProfile.setScore(0);
        userProfile.setCnt(0);
        userProfile = userProfileRepo.save(userProfile);

        return new UserProfileDto(userProfile);
    }

    @Override
    public UserProfileDto update(UserProfileDto userProfileDto) {

        UserProfile user = userProfileRepo.getById(userProfileDto.getId()); // hashwoori
        user.setNickname(userProfileDto.getNickname());
        user.setAddress(userProfileDto.getAddress());
        user.setPic(userProfileDto.getPic());
        user = userProfileRepo.save(user);

        return new UserProfileDto(user);
    }

    @Override
    public void delete(UserProfileDto userProfileDto) {

        UserProfile user = userProfileRepo.getById(userProfileDto.getId()); // hashwoori
        /* 회원과 관련된 글 목록과 댓글의 연관관계 끊기, 나머지는 삭제 */
        List<Article> articleList = articleRepo.findByUserProfile(user);
        for (Article article : articleList) {
            article.setUserProfile(null);
            articleRepo.save(article);
        }

        for(Comment comment: commentRepo.findByUserProfile(user)){
            comment.setUserProfile(null);
            commentRepo.save(comment);
        }

        userProfileRepo.delete(user);

    }
}
