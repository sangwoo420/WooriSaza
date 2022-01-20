package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.woori_saza.model.domain.Article;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {


}
