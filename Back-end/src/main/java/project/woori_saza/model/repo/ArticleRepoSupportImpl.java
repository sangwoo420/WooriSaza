package project.woori_saza.model.repo;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.QArticle;

import java.util.List;

@Transactional(readOnly = true)
public class ArticleRepoSupportImpl extends QuerydslRepositorySupport implements ArticleRepoSupport {

    public ArticleRepoSupportImpl(){
        super(Article.class);
    }

    @Override
    public List<Article> findByDefaultAddress(String address) {
        QArticle article = QArticle.article;
        JPQLQuery<Article> jpqlQuery = from(article);
        jpqlQuery.where(article.userProfile.address.contains(address));
        return jpqlQuery.fetch();
    }
}
