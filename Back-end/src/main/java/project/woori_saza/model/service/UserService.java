package project.woori_saza.model.service;

import project.woori_saza.model.domain.UserAuth;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.UserProfileDto;

public interface UserService {
    UserProfileDto login(String userAuthId);
    void register(String userAuthId, UserProfileDto userProfileDto);
}
