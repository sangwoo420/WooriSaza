package project.woori_saza.model.dto;

import lombok.Data;
import project.woori_saza.model.domain.Article;

import javax.persistence.ElementCollection;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleRequestDto {

    private String title;
    private String content;
    private String link;
    @ElementCollection
    private List<String> pic;

//    public Article toEntity() {
//        return Article.builder()
//                .title(title)
//                .content(content)
//                .link(link)
//                .pic(pic)
//                .build();
//    }
}
