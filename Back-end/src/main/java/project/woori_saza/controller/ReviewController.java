package project.woori_saza.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.dto.ReviewRequestDto;
import project.woori_saza.model.dto.ReviewResponseDto;
import project.woori_saza.model.service.ReviewService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public ResponseEntity<Map<String, Object>> insertReview(@RequestBody ReviewRequestDto reviewRequestDto) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus httpStatus = null;
        try {
            reviewService.insertReview(reviewRequestDto);
            httpStatus = HttpStatus.OK;
            result.put("success", true);
        } catch (RuntimeException e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<Map<String, Object>>(result, httpStatus);
    }

    @ApiOperation(value = "후기 수정", notes = "파티원들의 후기를 수정한다.", response = Map.class)
    @PutMapping("/{reviewId}")
    public ResponseEntity<Map<String, Object>> updateReview(@PathVariable("reviewId") Long reviewId, @RequestBody ReviewRequestDto reviewRequestDto) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus httpStatus = null;
        try {
            reviewService.updateReview(reviewId, reviewRequestDto);
            httpStatus = HttpStatus.OK;
            result.put("success", true);
        } catch (RuntimeException e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<Map<String, Object>>(result, httpStatus);
    }

    @ApiOperation(value = "후기 삭제", notes = "파티원들의 후기를 삭제한다.", response = Map.class)
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Map<String, Object>> deleteReview(@PathVariable("reviewId") Long reviewId) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus httpStatus = null;
        try {
            reviewService.deleteReview(reviewId);
            httpStatus = HttpStatus.OK;
            result.put("success", true);
        } catch (RuntimeException e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<Map<String, Object>>(result, httpStatus);
    }

}