package project.woori_saza.model.service;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.*;
import project.woori_saza.model.dto.ArticleAndPartyRequestDto;
import project.woori_saza.model.dto.ArticleRequestDto;
import project.woori_saza.model.dto.ArticleResponseDto;
import project.woori_saza.model.dto.PartyRequestDto;
import project.woori_saza.model.repo.*;
import project.woori_saza.util.GeoLocationUtil;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private PartyRepo partyRepo;

    @Autowired
    private MemberInfoRepo memberInfoRepo;

    @Autowired
    private UserProfileRepo userProfileRepo;

    @Autowired
    private GeoLocationUtil geoLocationUtil;

    @Autowired
    private ChatRoomRepo chatRoomRepo;

    @Autowired
    private ChatRoomJoinRepo chatRoomJoinRepo;

    @Autowired
    private ChatRoomService chatRoomService;

    @Override
    public ArticleResponseDto getArticle(Long articleId) {
        Article article = articleRepo.getById(articleId);
        ArticleResponseDto articleResponseDto = new ArticleResponseDto(article);

        return articleResponseDto;
    }

    // 게시글 전체 조회
    @Override
    public List<ArticleResponseDto> getArticleList(String profileId, String category, String range, String keyword) {

        Double[] lnglat = geoLocationUtil.parseLocationToLngLat(userProfileRepo.getById(profileId).getAddress());
        List<Article> articles = null;
        //1. 전부 없을때
        if (category == null && range == null && keyword == null) {
            articles = articleRepo.findAllByOrderByCreatedAtDesc();
        }
        //2. 카테고리만 있을때
        else if (range == null && keyword == null) {
            articles = articleRepo.findByCategoryOrderByCreatedAtDesc(Category.valueOf(category));
        }
        //3. 범위만 있을때
        else if (category == null && keyword == null) {
            articles = articleRepo.findByRange(lnglat, range);
        }
        //4. 검색어만 있을때
        else if (category == null && range == null) {
            articles = articleRepo.findByTitleContainingOrContentContainingOrderByCreatedAtDesc(keyword, keyword);
        }
        //5. 카테고리, 범위
        else if (keyword == null) {
            articles = articleRepo.findByCategoryAndRange(category, lnglat, range);
        }
        //6. 카테고리, 검색어
        else if (range == null) {
            articles = articleRepo.findByKeywordAndCategory(keyword, category);
        }
        //7. 범위, 검색어
        else if (category == null) {
            articles = articleRepo.findByKeywordAndRange(keyword, lnglat, range);
        }
        //8. 전부있을때
        else {
            articles = articleRepo.findByAllCondition(keyword, category, lnglat, range);
        }
        return articles.stream().map(ArticleResponseDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ArticleResponseDto insertArticle(ArticleAndPartyRequestDto articleAndPartyRequestDto) {

        Party party = new Party();
        party.setDeadline(LocalDateTime.parse(articleAndPartyRequestDto.getDeadline(), DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")));
        party.setTotalRecruitMember(articleAndPartyRequestDto.getTotalRecruitMember());
        party.setProduct(articleAndPartyRequestDto.getProduct());
        party.setTotalPrice(articleAndPartyRequestDto.getTotalPrice());
        party.setTotalProductCount(articleAndPartyRequestDto.getTotalProductCount());
        party.setTotalRecruitMember(articleAndPartyRequestDto.getTotalRecruitMember());
        party.setPenalty(articleAndPartyRequestDto.getPenalty());
        party.setCurrentRecruitMember(articleAndPartyRequestDto.getAmount());
        party.setFormChecked(false);
        party.setIsClosed(false);
        party = partyRepo.save(party);


        Article article = new Article();

        UserProfile userProfile = userProfileRepo.getById(articleAndPartyRequestDto.getProfileId());
        article.setUserProfile(userProfile);
        article.setTitle(articleAndPartyRequestDto.getTitle());
        article.setContent(articleAndPartyRequestDto.getContent());
        article.setLink(articleAndPartyRequestDto.getLink());
        article.setPic(articleAndPartyRequestDto.getPic());
        article.setCreatedAt(LocalDateTime.now());
        article.setCategory(articleAndPartyRequestDto.getCategory());
        article.setTag(null);
        article.setParty(party);
        article = articleRepo.save(article);

        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setIsBoss(true);
        memberInfo.setAmount(articleAndPartyRequestDto.getAmount());
        int calprice=(int)(articleAndPartyRequestDto.getTotalPrice() / articleAndPartyRequestDto.getTotalRecruitMember()) * articleAndPartyRequestDto.getAmount();
        memberInfo.setPrice(calprice);
        memberInfo.setParty(party);
        memberInfo.setUserProfile(userProfile);
        memberInfoRepo.save(memberInfo);

        /**
         * 채팅방 생성
         */
        ChatRoom chatRoom = chatRoomService.createChatRoom(article.getId(), article.getTitle());
        ChatRoomJoin chatRoomJoin = chatRoomService.createChatRoomJoin(chatRoom, userProfile);
        chatRoomRepo.save(chatRoom);
        chatRoomJoinRepo.save(chatRoomJoin);

        return new ArticleResponseDto(article);
    }


    @Override
    @Transactional
    public ArticleResponseDto updateArticle(ArticleAndPartyRequestDto articleAndPartyRequestDto, Long articleId) {

        Article article = articleRepo.getById(articleId);

        Party party = article.getParty();
        party.setDeadline(LocalDateTime.parse(articleAndPartyRequestDto.getDeadline(), DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")));
        party.setTotalRecruitMember(articleAndPartyRequestDto.getTotalRecruitMember());
        party.setProduct(articleAndPartyRequestDto.getProduct());
        party.setTotalPrice(articleAndPartyRequestDto.getTotalPrice());
        party.setTotalProductCount(articleAndPartyRequestDto.getTotalProductCount());
        party = partyRepo.save(party);

        article.setTitle(articleAndPartyRequestDto.getTitle());
        article.setContent(articleAndPartyRequestDto.getContent());
        article.setLink(articleAndPartyRequestDto.getLink());
        article.setPic(articleAndPartyRequestDto.getPic());
        article.setCategory(articleAndPartyRequestDto.getCategory());
        article.setTag(null);
        article.setParty(party);
        article = articleRepo.save(article);

        List<MemberInfo> memberInfos = memberInfoRepo.findAllByParty(party);
        for (MemberInfo memberInfo : memberInfos) {
            if (memberInfo.getIsBoss() == true) {
                memberInfo.setAmount(articleAndPartyRequestDto.getAmount());
                memberInfo.setPrice(articleAndPartyRequestDto.getTotalPrice() / articleAndPartyRequestDto.getAmount());
            }
        }
        return new ArticleResponseDto(article);
    }

    @Override
    @Transactional
    public void deleteArticle(Long articleId) {
        articleRepo.deleteById(articleId);
    }
}
