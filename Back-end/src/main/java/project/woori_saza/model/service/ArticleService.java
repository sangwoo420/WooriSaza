package project.woori_saza.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Category;
import project.woori_saza.model.dto.ArticleResponseDto;
import project.woori_saza.model.repo.ArticleRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepo articleRepo;

//    // 게시글 작성
//    @Transactional
//    public Long insertArticle(ArticleRequestDto articleRequestDto) {
//        return articleRepo.save(articleRequestDto.toEntity()).getId();
//    }

    // 게시글 전체 조회
    public List<ArticleResponseDto> getArticleList() {
        System.out.println("들어오나");
        List<Article> articles = articleRepo.findAll();
        System.out.println("articles"+articles);
        return articles.stream().map(ArticleResponseDto::new).collect(Collectors.toList());
    }
}
