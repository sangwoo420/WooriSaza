package project.woori_saza.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Category;
import project.woori_saza.model.dto.ArticleResponseDto;
import project.woori_saza.model.repo.ArticleRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ArticleService {

    private ArticleRepo articleRepo;

//    // 게시글 작성
//    @Transactional
//    public Long insertArticle(ArticleRequestDto articleRequestDto) {
//        return articleRepo.save(articleRequestDto.toEntity()).getId();
//    }

    // 게시글 전체 조회
    public List<ArticleResponseDto> getArticleList() {
        List<Article> articles = articleRepo.findAll();
        return articles.stream().map(ArticleResponseDto::new).collect(Collectors.toList());
    }
}
