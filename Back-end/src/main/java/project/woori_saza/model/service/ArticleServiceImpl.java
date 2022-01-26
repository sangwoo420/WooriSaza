package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Party;
import project.woori_saza.model.dto.ArticleAndPartyRequestDto;
import project.woori_saza.model.dto.ArticleRequestDto;
import project.woori_saza.model.dto.ArticleResponseDto;
import project.woori_saza.model.dto.PartyRequestDto;
import project.woori_saza.model.repo.ArticleRepo;
import project.woori_saza.model.repo.PartyRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
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
    @Override
    public List<ArticleResponseDto> getArticleList(String category, String range, String keyword) {

        List<Article> articles = articleRepo.findAll();
        return articles.stream().map(ArticleResponseDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void insertArticle(ArticleAndPartyRequestDto articleAndPartyRequestDto) {

        Party party = new Party();
        party.setDeadline(LocalDateTime.parse(articleAndPartyRequestDto.getDeadline(), DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")));
        party.setTotalRecruitMember(articleAndPartyRequestDto.getTotalRecruitMember());
        party.setProduct(articleAndPartyRequestDto.getProduct());
        party.setTotalPrice(articleAndPartyRequestDto.getTotalPrice());
        party.setTotalProductCount(articleAndPartyRequestDto.getTotalProductCount());
        party.setTotalRecruitMember(articleAndPartyRequestDto.getTotalRecruitMember());
        party.setCurrentRecruitMember(1);
        party.setFormChecked(false);
        party.setIsClosed(false);
        party = partyRepo.save(party);
//
        Article article = new Article();
        article.setTitle(articleAndPartyRequestDto.getTitle());
        article.setContent(articleAndPartyRequestDto.getContent());
        article.setLink(articleAndPartyRequestDto.getLink());
        article.setPic(articleAndPartyRequestDto.getPic());
        article.setCreatedAt(LocalDateTime.now());
        article.setCategory(articleAndPartyRequestDto.getCategory());
        article.setTag(null);
        article.setParty(party);
        article = articleRepo.save(article);
    }


    @Override
    @Transactional
    public void updateArticle(ArticleAndPartyRequestDto articleAndPartyRequestDto, Long articleId) {

        Article article = articleRepo.getById(articleId);

        Party party = article.getParty();
        party.setDeadline(LocalDateTime.parse(articleAndPartyRequestDto.getDeadline(), DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")));
        party.setTotalRecruitMember(articleAndPartyRequestDto.getTotalRecruitMember());
        party.setProduct(articleAndPartyRequestDto.getProduct());
        party.setTotalPrice(articleAndPartyRequestDto.getTotalPrice());
        party.setTotalProductCount(articleAndPartyRequestDto.getTotalProductCount());
        partyRepo.save(party);

        article.setTitle(articleAndPartyRequestDto.getTitle());
        article.setContent(articleAndPartyRequestDto.getContent());
        article.setLink(articleAndPartyRequestDto.getLink());
        article.setPic(articleAndPartyRequestDto.getPic());
        article.setCategory(articleAndPartyRequestDto.getCategory());
        article.setTag(null);
        article.setParty(party);
        articleRepo.save(article);

    }

    @Override
    @Transactional
    public void deleteArticle(Long articleId) {
        articleRepo.deleteById(articleId);
    }
}
