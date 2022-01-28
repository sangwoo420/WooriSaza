package project.woori_saza.model.service;

import project.woori_saza.model.dto.UserProfileDto;

public interface UserService {
    UserProfileDto login(String userAuthId);
    UserProfileDto register(UserProfileDto userProfileDto);
    UserProfileDto update(UserProfileDto userProfileDto);
    void delete(UserProfileDto userProfileDto);
}
