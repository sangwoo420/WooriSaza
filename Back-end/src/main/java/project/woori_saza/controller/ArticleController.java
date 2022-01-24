package project.woori_saza.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.dto.ArticleResponseDto;
import project.woori_saza.model.repo.ArticleRepo;
import project.woori_saza.model.service.ArticleService;

import java.util.List;

@RestController
@RequestMapping("/article")
@Api(tags = {"User Controller"})
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleRepo articleRepo;

 //   @ApiOperation(value="게시글 리스트",notes = "게시글 리스트를 반환다.",response = Map.class)
    @GetMapping("/list")
    public ResponseEntity<List<ArticleResponseDto>> GetArticleList(){
        List<ArticleResponseDto> list= articleService.getArticleList();
        return new ResponseEntity<List<ArticleResponseDto>>(list, HttpStatus.OK);
    }

//    @GetMapping("/list")
//    public ResponseEntity<List<Article>> GetArticleList(){
//        List<Article> list= articleRepo.findAll();
//        return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
//    }


}
