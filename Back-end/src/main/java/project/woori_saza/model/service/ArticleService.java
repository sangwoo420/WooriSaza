package project.woori_saza.model.service;

import project.woori_saza.model.dto.ArticleAndPartyRequestDto;
import project.woori_saza.model.dto.ArticleRequestDto;
import project.woori_saza.model.dto.ArticleResponseDto;
import project.woori_saza.model.dto.PartyRequestDto;

import java.util.List;

public interface ArticleService {
    ArticleResponseDto getArticle(Long articleId);
    List<ArticleResponseDto> getArticleList(String category, String range, String keyword);
    void insertArticle(ArticleAndPartyRequestDto articleAndPartyRequestDto);
    void updateArticle(ArticleAndPartyRequestDto articleAndPartyRequestDto, Long articleId);
    void deleteArticle(Long articleId);

}
