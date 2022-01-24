package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Party;
import project.woori_saza.model.dto.ArticleRequestDto;
import project.woori_saza.model.dto.ArticleResponseDto;
import project.woori_saza.model.repo.ArticleRepo;
import project.woori_saza.model.repo.PartyRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private PartyRepo partyRepo;

//    // 게시글 작성
//    @Transactional
//    public Long insertArticle(ArticleRequestDto articleRequestDto) {
//        return articleRepo.save(articleRequestDto.toEntity()).getId();
//    }

    @Override
    public ArticleResponseDto getArticle(Long articleId) {
        return null;
    }

    // 게시글 전체 조회
    public List<ArticleResponseDto> getArticleList() {
        System.out.println("들어오나");
        List<Article> articles = articleRepo.findAll();
        System.out.println("articles"+articles);
        return articles.stream().map(ArticleResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public void insertArticle(Party party, ArticleRequestDto articleRequestDto) {
        partyRepo.save(party);
        Article article = new Article();
        article.setTitle(articleRequestDto.getTitle());
        article.setContent(articleRequestDto.getContent());
        article.setLink(articleRequestDto.getLink());
        article.setPic(articleRequestDto.getPic());
        article.setCreatedAt(LocalDateTime.now());
        article.setCategory(null);
        article.setTag(null);
        article.setParty(party);
        articleRepo.save(article);
    }


    @Override
    public void updateArticle(ArticleRequestDto article) {

    }

    @Override
    public void deleteArticle(Long articleId) {

    }
}
