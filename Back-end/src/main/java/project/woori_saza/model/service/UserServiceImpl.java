package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.woori_saza.model.domain.UserAuth;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.repo.UserAuthRepo;
import project.woori_saza.model.repo.UserProfileRepo;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserAuthRepo userAuthRepo;

    @Autowired
    UserProfileRepo userProfileRepo;

    @Override
    public UserProfile login(String userAuthId) {
        UserProfile userProfile= userProfileRepo.findByUserAuth(userAuthRepo.getById(userAuthId));
        return userProfile;
    }

    @Override
    public void register(UserAuth userAuth, UserProfile userProfile) {
        userAuthRepo.save(userAuth);
        userProfile.setUserAuth(userAuth);
        userProfileRepo.save(userProfile);
    }
}
