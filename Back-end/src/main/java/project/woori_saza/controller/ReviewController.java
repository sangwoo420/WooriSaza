package project.woori_saza.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.dto.ReviewRequestDto;
import project.woori_saza.model.dto.ReviewResponseDto;
import project.woori_saza.model.service.ReviewService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @ApiOperation(value = "후기 조회", notes = "사용자에게 달린 후기들을 불러온다.", response = Map.class)
    @GetMapping("/{profileId}")
    public ResponseEntity<Map<String, Object>> getReviewList(@PathVariable String profileId) {
        Map<String, Object> result = new HashMap<>();
        List<ReviewResponseDto> reviewList = null;
        HttpStatus httpStatus = null;

        try {
            reviewList = reviewService.getReviewList(profileId);

            httpStatus = HttpStatus.OK;
        } catch (RuntimeException e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        result.put("reviewList", reviewList);
        return new ResponseEntity<Map<String, Object>>(result, httpStatus);
    }

    @ApiOperation(value = "후기 작성", notes = "파티원들의 후기를 작성한다.")
    @PostMapping
    public Long insertReview(@RequestBody ReviewRequestDto reviewRequestDto) {
        return reviewService.insertReview(reviewRequestDto);
    }

    @ApiOperation(value = "후기 수정", notes = "파티원들의 후기를 수정한다.")
    @PutMapping
    public Long updateReview(@RequestBody ReviewRequestDto reviewRequestDto) {
        return reviewService.updateReview(reviewRequestDto);
    }

    @ApiOperation(value = "후기 삭제", notes = "파티원들의 후기를 수정한다.")
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
    }

}
