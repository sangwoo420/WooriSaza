package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.Review;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.ReviewRequestDto;
import project.woori_saza.model.dto.ReviewResponseDto;
import project.woori_saza.model.repo.ReviewRepo;
import project.woori_saza.model.repo.UserProfileRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepo reviewRepo;

    @Autowired
    UserProfileRepo userProfileRepo;

    @Override
    public List<ReviewResponseDto> getReviewList(String profileId) {

        UserProfile user = userProfileRepo.getById(profileId);
        List<Review> reviews = reviewRepo.findByToUserOrderByDateDesc(user);
        return reviews.stream().map(ReviewResponseDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void insertReview(ReviewRequestDto reviewRequestDto) {
        Review review = reviewRequestDto.toEntity();
        review.setDate(LocalDateTime.now());
        review.setFromUser(userProfileRepo.getById(reviewRequestDto.getFromUser()));

        UserProfile toUser = userProfileRepo.getById(reviewRequestDto.getToUser());
        review.setToUser(toUser);
        toUser.setScore(toUser.getScore() + review.getScore());
        toUser.setCnt(toUser.getCnt()+1);
    }

    @Override
    @Transactional
    public void updateReview(Long reviewId, ReviewRequestDto reviewRequestDto) {
        Review review = reviewRepo.getById(reviewId);
        review.setContent(reviewRequestDto.getContent());
        review.setScore(reviewRequestDto.getScore());
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        reviewRepo.deleteById(reviewId);
    }
}
