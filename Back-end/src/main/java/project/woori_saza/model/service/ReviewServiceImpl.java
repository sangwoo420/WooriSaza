package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Comment;
import project.woori_saza.model.domain.Review;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.ArticleResponseDto;
import project.woori_saza.model.dto.ReviewRequestDto;
import project.woori_saza.model.dto.ReviewResponseDto;
import project.woori_saza.model.repo.ReviewRepo;
import project.woori_saza.model.repo.UserProfileRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepo reviewRepo;

    @Autowired
    UserProfileRepo userProfileRepo;

    @Override
    public List<ReviewResponseDto> getReviewList(String profileId) {

        UserProfile user = userProfileRepo.getById(profileId);
        System.out.println("user = " + user);
        System.out.println("------------------------------------------------");
        List<Review> reviews = reviewRepo.findByToUser(user);
        return reviews.stream().map(ReviewResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public Long insertReview(ReviewRequestDto reviewRequestDto) {
        Review review = reviewRequestDto.toEntity();
//        review.setFromUser(userProfileRepo.get; -> 현재 로그인한 사용자의 정보 가져오는 방법?
        return reviewRepo.save(reviewRequestDto.toEntity()).getId();
    }

    @Override
    public Long updateReview(ReviewRequestDto reviewRequestDto) {
//        Optional<Review> review = reviewRepo.getById(reviewRequestDto.getId());

//        review.update(reviewRequestDto.getContent(), ...);

        return null;
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepo.deleteById(reviewId);
    }
}
