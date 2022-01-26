package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.woori_saza.model.domain.UserAuth;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.UserProfileDto;
import project.woori_saza.model.repo.UserAuthRepo;
import project.woori_saza.model.repo.UserProfileRepo;
import project.woori_saza.util.HashEncoder;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserAuthRepo userAuthRepo;

    @Autowired
    UserProfileRepo userProfileRepo;

    @Autowired
    HashEncoder hashEncoder;

    @Override
    public UserProfileDto login(String userAuthId) {
        UserAuth userAuth = userAuthRepo.getById(hashEncoder.encode(userAuthId));
        System.out.println(userAuthId + " : " +userAuth.getId());
        UserProfile userProfile= userProfileRepo.findByUserAuth(userAuth);
        return userProfile == null ? null : new UserProfileDto(userProfile);
    }

    @Override
    public UserProfileDto register(UserProfileDto userProfileDto) {

        UserAuth userAuth = new UserAuth(hashEncoder.encode(userProfileDto.getId()),false,null);
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
}
