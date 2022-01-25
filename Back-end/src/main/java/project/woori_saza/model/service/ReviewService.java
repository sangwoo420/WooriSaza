package project.woori_saza.model.service;

import project.woori_saza.model.dto.*;

import java.util.List;

public interface ReviewService {

    List<ReviewResponseDto> getReviewList(String profileId);
    Long insertReview(ReviewRequestDto reviewRequestDto);
    Long updateReview(ReviewRequestDto reviewRequestDto);
    void deleteReview(Long reviewId);
}
