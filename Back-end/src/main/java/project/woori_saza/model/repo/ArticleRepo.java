package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Category;
import project.woori_saza.model.domain.UserProfile;

import java.util.List;

public interface ArticleRepo extends JpaRepository<Article, Long>, ArticleRepoSupport{

    List<Article> findAllOrderByCreatedAtDesc();

    List<Article> findByUserProfileOrderByCreatedAtDesc(UserProfile userProfile);

    List<Article> findByCategoryOrderByCreatedAtDesc(Category category);

    List<Article> findByTitleContainingOrContentContainingOrderByCreatedAtDesc(String title, String content);
}
