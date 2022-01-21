package project.woori_saza.model.service;

import project.woori_saza.model.domain.UserAuth;
import project.woori_saza.model.domain.UserProfile;

public interface UserService {
    UserProfile login(String userAuthId);
    void register(UserAuth userAuth, UserProfile userProfile);
}
