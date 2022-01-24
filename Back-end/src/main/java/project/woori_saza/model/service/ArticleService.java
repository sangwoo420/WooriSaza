package project.woori_saza.model.service;

import project.woori_saza.model.dto.ArticleRequestDto;
import project.woori_saza.model.dto.ArticleResponseDto;
import project.woori_saza.model.dto.PartyRequestDto;

import java.util.List;

public interface ArticleService {
    ArticleResponseDto getArticle(Long articleId);
    List<ArticleResponseDto> getArticleList(String... options);
    void insertArticle(PartyRequestDto partyRequestDto, ArticleRequestDto articleRequestDto);
    void updateArticle(PartyRequestDto partyRequestDto, ArticleRequestDto articleRequestDto, Long articleId);
    void deleteArticle(Long articleId);

}
