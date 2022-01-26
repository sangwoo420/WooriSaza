package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Category;
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

    @Override
    public ArticleResponseDto getArticle(Long articleId) {
        Article article = articleRepo.getById(articleId);
        ArticleResponseDto articleResponseDto = new ArticleResponseDto(article);

        return articleResponseDto;
    }

    // 게시글 전체 조회
    @Override
    public List<ArticleResponseDto> getArticleList(String category, String range, String keyword) {

        List<Article> articles = null;
        //1. 전부 없을때
        if(category == null && range == null && keyword == null){
            articles = articleRepo.findAll();
        }
        //2. 카테고리만 있을때
        else if(range == null && keyword == null){
            articles = articleRepo.findByCategory(Category.valueOf(category));
        }
        //3. 범위만 있을때
        else if(category == null && keyword == null){

        }
        //4. 검색어만 있을때
        else if(category == null && range == null){
            articles = articleRepo.findByTitleContainingOrContentContaining(keyword, keyword);
        }
        //5. 카테고리, 범위
        else if(keyword == null){

        }
        //6. 카테고리, 검색어
        else if(range == null){

        }
        //7. 범위, 검색어
        else if(category == null){

        }
        //8. 전부있을때
        else {

        }

        return articles.stream().map(ArticleResponseDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ArticleResponseDto insertArticle(ArticleAndPartyRequestDto articleAndPartyRequestDto) {

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

        return new ArticleResponseDto(article);
    }


    @Override
    @Transactional
    public ArticleResponseDto updateArticle(ArticleAndPartyRequestDto articleAndPartyRequestDto, Long articleId) {

        Article article = articleRepo.getById(articleId);

        Party party = article.getParty();
        party.setDeadline(LocalDateTime.parse(articleAndPartyRequestDto.getDeadline(), DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")));
        party.setTotalRecruitMember(articleAndPartyRequestDto.getTotalRecruitMember());
        party.setProduct(articleAndPartyRequestDto.getProduct());
        party.setTotalPrice(articleAndPartyRequestDto.getTotalPrice());
        party.setTotalProductCount(articleAndPartyRequestDto.getTotalProductCount());
        party = partyRepo.save(party);

        article.setTitle(articleAndPartyRequestDto.getTitle());
        article.setContent(articleAndPartyRequestDto.getContent());
        article.setLink(articleAndPartyRequestDto.getLink());
        article.setPic(articleAndPartyRequestDto.getPic());
        article.setCategory(articleAndPartyRequestDto.getCategory());
        article.setTag(null);
        article.setParty(party);
        article = articleRepo.save(article);

        return new ArticleResponseDto(article);
    }

    @Override
    @Transactional
    public void deleteArticle(Long articleId) {
        articleRepo.deleteById(articleId);
    }
}
