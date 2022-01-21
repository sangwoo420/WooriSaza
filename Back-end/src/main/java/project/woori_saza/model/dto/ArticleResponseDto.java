package project.woori_saza.model.dto;

import project.woori_saza.model.domain.Article;

import javax.persistence.ElementCollection;
import java.time.LocalDateTime;
import java.util.List;

public class ArticleResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String link;
    @ElementCollection
    private List<String> pic;

    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.link = article.getLink();
        this.pic = article.getPic();
    }
}
