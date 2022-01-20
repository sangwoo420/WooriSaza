package project.woori_saza;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.repo.ArticleRepo;

@SpringBootTest
public class ArticleTest {

    @Autowired
    ArticleRepo repo;

    @Test
    void ArticleTest(){
        Article article = new Article();
    }
}
