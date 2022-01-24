package project.woori_saza.model.service;

import project.woori_saza.model.domain.Party;
import project.woori_saza.model.dto.ArticleRequestDto;
import project.woori_saza.model.dto.ArticleResponseDto;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    ArticleResponseDto getArticle(Long articleId);
    List<ArticleResponseDto> getArticleList();
    void insertArticle(Party party, ArticleRequestDto articleRequestDto);
    void updateArticle(ArticleRequestDto article);
    void deleteArticle(Long articleId);

}
