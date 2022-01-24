package project.woori_saza.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.domain.Party;
import project.woori_saza.model.dto.ArticleRequestDto;
import project.woori_saza.model.dto.ArticleResponseDto;
import project.woori_saza.model.repo.ArticleRepo;
import project.woori_saza.model.service.ArticleService;
import project.woori_saza.model.service.ArticleServiceImpl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    ObjectMapper objectMapper;

 //   @ApiOperation(value="게시글 리스트",notes = "게시글 리스트를 반환다.",response = Map.class)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getArticleList(@RequestParam Map<String, String> params){
        Map<String, Object> result = new HashMap<>();
        List<ArticleResponseDto> articleList = null;
        HttpStatus httpStatus = null;

        try {
            articleList = articleService.getArticleList();
            httpStatus = HttpStatus.OK;
        } catch (RuntimeException e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }


        result.put("articleList", articleList);

        return new ResponseEntity<>(result, httpStatus);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<Map<String, Object>> getArticle(@PathVariable String articleId){
        Map<String, Object> result = new HashMap<>();
        HttpStatus httpStatus = null;
        ArticleResponseDto article = null;
        try {
            article = articleService.getArticle(Long.parseLong(articleId));
            httpStatus = HttpStatus.OK;
        }catch (RuntimeException e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        result.put("article", article);
        return new ResponseEntity<>(result, httpStatus);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> insertArticle(@RequestBody Map<String, String> body){
        Map<String, Object> result = new HashMap<>();
        HttpStatus httpStatus = null;
        try{
            Party party = new Party();
            party.setDeadline(LocalDateTime.parse(body.get("deadline")));
            party.setProduct(body.get("product"));
            party.setTotalPrice(Integer.parseInt(body.get("totalprice")));
            party.setTotalProductCount(Integer.parseInt(body.get("productcount")));
            party.setTotalRecruitMember(Integer.parseInt(body.get("recruitmember")));
            ArticleRequestDto articleRequestDto = new ArticleRequestDto();
            articleRequestDto.setTitle(body.get("title"));
            articleRequestDto.setContent(body.get("content"));
            articleRequestDto.setLink(body.get("link"));
            List<String> picList = objectMapper.readValue(body.get("piclist"), List.class);
            articleRequestDto.setPic(picList);
            articleService.insertArticle(party, articleRequestDto);
            httpStatus = HttpStatus.OK;
            result.put("success", true);
        }catch (RuntimeException | JsonProcessingException e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<>(result, httpStatus);
    }

//    @GetMapping("/list")
//    public ResponseEntity<List<Article>> GetArticleList(){
//        List<Article> list= articleRepo.findAll();
//        return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
//    }


}
