package project.woori_saza.model.service;

import org.springframework.web.multipart.MultipartFile;
import project.woori_saza.model.dto.UserProfileDto;

public interface UserService {
    UserProfileDto login(String userAuthId);
 //   UserProfileDto register(UserProfileDto userProfileDto, MultipartFile multipartFile) throws Exception;
    UserProfileDto register(UserProfileDto userProfileDto);
    void upload(MultipartFile multipartFile) throws Exception;
    UserProfileDto update(UserProfileDto userProfileDto);
    void delete(UserProfileDto userProfileDto);

    UserProfileDto getUserProfileInfo(String profileId);
}
