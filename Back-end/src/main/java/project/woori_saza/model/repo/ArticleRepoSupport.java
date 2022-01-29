package project.woori_saza.model.repo;

import project.woori_saza.model.domain.Article;

import java.util.List;

public interface ArticleRepoSupport {

    List<Article> findByDefaultAddress(String address);
}
