package project.woori_saza.model.dto;

import lombok.Data;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Category;
import project.woori_saza.model.domain.Tag;

import javax.persistence.ElementCollection;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleResponseDto {
// 보여줄때
    private Long id;
    private String title;
    private String content;
    private String product;
    private LocalDateTime createdAt;
    private String link;
    @ElementCollection
    private List<String> pic;
    private Integer totalPrice;
    private Integer totalRecruitMember;
    private Integer myPrice;
    private Integer currentRecruitMember;
    private Tag tag;
    private Category category;

    // 찜 유무..


    public ArticleResponseDto(Article article) {
        id = article.getId();
        title = article.getTitle();
        product = article.getParty().getProduct();
        content = article.getContent();
        createdAt = article.getCreatedAt();
        link = article.getLink();
        pic = article.getPic();
        totalPrice = article.getParty().getTotalPrice();
        totalRecruitMember = article.getParty().getTotalRecruitMember();
        currentRecruitMember = article.getParty().getCurrentRecruitMember();
        myPrice = article.getParty().getTotalPrice()/totalRecruitMember;
        tag = article.getTag();
        category = article.getCategory();
    }
}