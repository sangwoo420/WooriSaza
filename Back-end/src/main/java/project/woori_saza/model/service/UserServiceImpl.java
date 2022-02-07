package project.woori_saza.model.service;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Comment;
import project.woori_saza.model.domain.UserAuth;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.UserProfileDto;
import project.woori_saza.model.repo.ArticleRepo;
import project.woori_saza.model.repo.CommentRepo;
import project.woori_saza.model.repo.UserAuthRepo;
import project.woori_saza.model.repo.UserProfileRepo;
import project.woori_saza.util.GeoLocationUtil;
import project.woori_saza.util.HashEncoder;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    GeoLocationUtil geoLocationUtil;

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
        System.out.println(userAuthId + " : " + userAuth.getId());
        UserProfile userProfile = userProfileRepo.findByUserAuth(userAuth);
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
        Double[] lnglat = geoLocationUtil.parseLocationToLngLat(userProfile.getAddress());
        userProfile.setLng(lnglat[0]);
        userProfile.setLat(lnglat[1]);
        userProfile.setScore(0);
        userProfile.setCnt(0);

        userProfile = userProfileRepo.save(userProfile);

        return new UserProfileDto(userProfile);

    }

    //    @Override
//    public UserProfileDto register(UserProfileDto userProfileDto, MultipartFile multipartFile) throws Exception {
//
//        UserAuth userAuth = new UserAuth(hashEncoder.encode(userProfileDto.getId()), false, null);
//        userAuth = userAuthRepo.save(userAuth);
//
//        UserProfile userProfile = new UserProfile(userProfileDto);
//        userProfile.setId(hashEncoder.encode(userAuth.getId())); // double hashed id
//        userProfile.setUserAuth(userAuth);
//        userProfile.setJoinDate(LocalDateTime.now());
//        Double[] lnglat = geoLocationUtil.parseLocationToLngLat(userProfile.getAddress());
//        userProfile.setLng(lnglat[0]);
//        userProfile.setLat(lnglat[1]);
//        userProfile.setScore(0);
//        userProfile.setCnt(0);
//
//        //파일 저장
//        if(multipartFile==null){
//            userProfile.setPic(null);
//        }else{
//            //저장경로
//            //  String savePath="i6c102.p.ssafy.io"+separ+"home"+separ+"ubuntu"+separ+"images";
//            //   String savePath="i6c102.p.ssafy.io"+separ+"images";
//           // String savaPath="http://i6c102.p.ssafy.io/upload";
//            // "/home/ubuntu/images/"
//            // 파일 정보
//            String originFilename = multipartFile.getOriginalFilename();
//            String extension = originFilename.substring(originFilename.length()-3);
//
//            if(!(extension.equals("jpg") || extension.equals("png"))){
//                throw new FileUploadException("파일 확장자가 jpg나 png가 아닙니다.");
//            }
//            //파일이름 랜덤으로 만들기
//            String saveFileName = UUID.randomUUID().toString() + originFilename.substring(originFilename.lastIndexOf(".")); //랜덤이름+확장자
//            System.out.println("랜덤이름 출력"+saveFileName);
//         //      String filePath=savaPath+saveFileName;
//
////            HttpHeaders headers = new HttpHeaders();
////            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
////
////            MultiValueMap<String, Object> body= new LinkedMultiValueMap<>();
////            body.add("file", getTestFile());
////
////            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
////
////            String serverUrl = "http://localhost:8082/spring-rest/fileserver/singlefileupload/";
////
////            RestTemplate restTemplate = new RestTemplate();
////            ResponseEntity<String> response = restTemplate
////                    .postForEntity(serverUrl, requestEntity, String.class);
//
//
//            // 파일 이름은 db에 저장
//            userProfile.setPic(saveFileName);
//        }
//        userProfile = userProfileRepo.save(userProfile);
//
//        return new UserProfileDto(userProfile);
//    }


    @Override
    public void upload(MultipartFile multipartFile) throws Exception {

            // 파일 정보
            String originFilename = multipartFile.getOriginalFilename(); //파일이름
            String extension = originFilename.substring(originFilename.length()-3); //확장자

            // 사진인지 체크
            if(!(extension.equals("jpg") || extension.equals("png"))){
                throw new FileUploadException("파일 확장자가 jpg나 png가 아닙니다.");
            }
           //파일이름 랜덤으로 만들기
            String saveFileName = UUID.randomUUID().toString() + originFilename.substring(originFilename.lastIndexOf(".")); //랜덤이름+확장자
            System.out.println("랜덤이름 출력"+saveFileName);
          //      String filePath=savaPath+saveFileName;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

           MultiValueMap<String, Object> body= new LinkedMultiValueMap<>();
           body.add("file", multipartFile.getResource());

           HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

           String serverUrl = "http://i6c102.p.ssafy.io:3000/upload";

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate
                    .postForEntity(serverUrl, requestEntity, String.class);


    }

    @Override
    public UserProfileDto update(UserProfileDto userProfileDto) {

        UserProfile user = userProfileRepo.getById(userProfileDto.getId()); // hashwoori
        user.setNickname(userProfileDto.getNickname());
        user.setAddress(userProfileDto.getAddress());
        Double[] lnglat = geoLocationUtil.parseLocationToLngLat(user.getAddress());
        user.setLng(lnglat[0]);
        user.setLat(lnglat[1]);
        user.setPic(userProfileDto.getPic());
        user = userProfileRepo.save(user);

        return new UserProfileDto(user);
    }

    @Override
    public void delete(UserProfileDto userProfileDto) {
        UserProfile user = userProfileRepo.getById(userProfileDto.getId()); // hashwoori

        /* 회원과 관련된 글 목록과 댓글의 연관관계 끊기, 나머지는 삭제 */
        List<Article> articleList = articleRepo.findByUserProfileOrderByCreatedAtDesc(user);
        for (Article article : articleList) {
            article.setUserProfile(null);
            articleRepo.save(article);
        }

        List<Comment> commentList = commentRepo.findByUserProfile(user);
        for (Comment comment : commentList) {
            comment.setUserProfile(null);
            commentRepo.save(comment);
        }

        userProfileRepo.delete(user);

    }

    @Override
    public UserProfileDto getUserProfileInfo(String profileId) {
        return new UserProfileDto(userProfileRepo.getById(profileId));
    }
}
