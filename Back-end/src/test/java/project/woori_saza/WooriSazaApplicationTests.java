package project.woori_saza;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.repo.ArticleRepo;
import project.woori_saza.util.GeoLocationUtil;

@SpringBootTest
class WooriSazaApplicationTests {

	@Autowired
	ArticleRepo articleRepo;

	@Autowired
	GeoLocationUtil geoLocationUtil;

	@Test
	void contextLoads() {
		for(Article a : articleRepo.findByRange(geoLocationUtil.parseLocationToLngLat("광주광역시 북구 면앙로6번길 42"), "1000")){
			System.out.println(a.getUserProfile().getAddress());
		}
	}

}
