package project.woori_saza;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.repo.ArticleRepo;

@SpringBootTest
class WooriSazaApplicationTests {

	@Autowired
	ArticleRepo articleRepo;

	@Test
	void contextLoads() {
		for(Article a : articleRepo.findByDefaultAddress("광주광역시")){
			System.out.println(a.getUserProfile());
		}
	}

}
