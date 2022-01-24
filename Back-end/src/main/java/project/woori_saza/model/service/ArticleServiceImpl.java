package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Party;
import project.woori_saza.model.dto.ArticleRequestDto;
import project.woori_saza.model.dto.ArticleResponseDto;
import project.woori_saza.model.dto.PartyRequestDto;
import project.woori_saza.model.repo.ArticleRepo;
import project.woori_saza.model.repo.PartyRepo;

import java.time.LocalDateTime;
import java.util.List;
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
        Article article = articleRepo.getById(articleId);
        ArticleResponseDto articleResponseDto = new ArticleResponseDto(article);
        return articleResponseDto;
    }

    // 게시글 전체 조회
    public List<ArticleResponseDto> getArticleList(String... options) {

        System.out.println("들어오나");
        List<Article> articles = articleRepo.findAll();
        System.out.println("articles"+articles);
        return articles.stream().map(ArticleResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public void insertArticle(PartyRequestDto partyRequestDto, ArticleRequestDto articleRequestDto) {

        Party party = new Party();
        party.setDeadline(partyRequestDto.getDeadline());
        party.setTotalRecruitMember(partyRequestDto.getTotalRecruitMember());
        party.setProduct(partyRequestDto.getProduct());
        party.setTotalPrice(partyRequestDto.getTotalPrice());
        party.setTotalProductCount(partyRequestDto.getTotalProductCount());
        partyRepo.save(party);

        Article article = new Article();
        article.setTitle(articleRequestDto.getTitle());
        article.setContent(articleRequestDto.getContent());
        article.setLink(articleRequestDto.getLink());
        article.setPic(articleRequestDto.getPic());
        article.setCreatedAt(LocalDateTime.now());
        article.setCategory(articleRequestDto.getCategory());
        article.setTag(null);
        article.setParty(party);
        articleRepo.save(article);
    }


    @Override
    public void updateArticle(PartyRequestDto partyRequestDto, ArticleRequestDto articleRequestDto, Long articleId) {

        Article article = articleRepo.getById(articleId);

        Party party = article.getParty();
        party.setDeadline(partyRequestDto.getDeadline());
        party.setTotalRecruitMember(partyRequestDto.getTotalRecruitMember());
        party.setProduct(partyRequestDto.getProduct());
        party.setTotalPrice(partyRequestDto.getTotalPrice());
        party.setTotalProductCount(partyRequestDto.getTotalProductCount());
        partyRepo.save(party);

        article.setTitle(articleRequestDto.getTitle());
        article.setContent(articleRequestDto.getContent());
        article.setLink(articleRequestDto.getLink());
        article.setPic(articleRequestDto.getPic());
        article.setCategory(articleRequestDto.getCategory());
        article.setTag(null);
        article.setParty(party);
        articleRepo.save(article);

    }

    @Override
    public void deleteArticle(Long articleId) {
        articleRepo.deleteById(articleId);
    }
}
