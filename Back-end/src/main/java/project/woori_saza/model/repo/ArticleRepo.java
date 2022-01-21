package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Category;
import project.woori_saza.model.domain.UserProfile;

import java.util.List;

public interface ArticleRepo extends JpaRepository<Article, Long> {

    List<Article> findByUserProfile(UserProfile userProfile);

    List<Article> findByCategory(Category category);


}
