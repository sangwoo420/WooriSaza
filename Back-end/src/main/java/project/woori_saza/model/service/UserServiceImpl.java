package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.woori_saza.model.domain.UserAuth;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.UserProfileDto;
import project.woori_saza.model.repo.UserAuthRepo;
import project.woori_saza.model.repo.UserProfileRepo;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserAuthRepo userAuthRepo;

    @Autowired
    UserProfileRepo userProfileRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserProfileDto login(String userAuthId) {
        UserProfile userProfile= userProfileRepo.findByUserAuth(userAuthRepo.getById(passwordEncoder.encode(userAuthId)));
        return userProfile == null ? null : new UserProfileDto(userProfile);
    }

    @Override
    public void register(String userAuthId, UserProfileDto userProfileDto) {
        //userAuthRepo.save(userAuth);
        //userProfile.setUserAuth(userAuth);
        //userProfileRepo.save(userProfile);
        UserAuth userAuth = new UserAuth(passwordEncoder.encode(userAuthId),false,null);
        userAuthRepo.save(userAuth);
        UserProfile userProfile = new UserProfile(userProfileDto);
        userProfile.setId(passwordEncoder.encode(userAuth.getId())); // double hashed id
        userProfile.setUserAuth(userAuth);
        userProfileRepo.save(userProfile);

    }
}
