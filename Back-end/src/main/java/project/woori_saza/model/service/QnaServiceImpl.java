package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.Qna;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.QnaDto;
import project.woori_saza.model.repo.QnaRepo;
import project.woori_saza.model.repo.UserProfileRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class QnaServiceImpl implements QnaService{

    @Autowired
    QnaRepo qnaRepo;
    @Autowired
    UserProfileRepo userProfileRepo;

    /**
     * 내가 작성한 모든 문의 리스트
     */
    @Override
    public List<QnaDto> getMyQnaList(String profileId) {
        System.out.println("===1:1 문의 리스트===");
        UserProfile user = userProfileRepo.getById(profileId);
        List<Qna> qnas = qnaRepo.findByUserProfile(user);
        return qnas.stream().map(QnaDto::new).collect(Collectors.toList());
    }

    /**
     * 관리자가 보는 문의 리스트
     */
    @Override
    public List<QnaDto> getQnaList() {
        System.out.println("===관리자가 보는 모든 문의 리스트===");
        List<Qna> qnas = qnaRepo.findAll();
        return qnas.stream().map(QnaDto::new).collect(Collectors.toList());
    }

    /**
     * 1:1 문의 작성 - 작성자
     */
    @Override
    @Transactional
    public void insertQna(QnaDto qnaDto) {
        System.out.println("===1:1 문의 작성===");
        UserProfile user = userProfileRepo.getById(qnaDto.getProfileId());
        Qna qna = new Qna();
        qna.setId(qnaDto.getId());
        qna.setCategory(qnaDto.getCategory());
        qna.setContent(qnaDto.getContent());
        qna.setTitle(qnaDto.getTitle());
        qna.setUserProfile(user);
        qnaRepo.save(qna);
    }

    /**
     * 1:1 문의 수정 - 작성자
     */
    @Override
    @Transactional
    public void updateQna(QnaDto qnaDto) {
        System.out.println("===1:1 문의 수정===");
        UserProfile user = userProfileRepo.getById(qnaDto.getProfileId());
        Qna qna = qnaRepo.getById(qnaDto.getId());
        qna.setCategory(qnaDto.getCategory());
        qna.setTitle(qnaDto.getTitle());
        qna.setContent(qnaDto.getContent());
        qna.setUserProfile(user);
        qnaRepo.save(qna);
    }

    /**
     * 1:1 문의 삭제 - 작성자, 관리자
     */
    @Override
    @Transactional
    public void deleteQna(Long qnaId) {
        System.out.println("===1:1 문의 삭제===");
        qnaRepo.deleteById(qnaId);
    }

    /**
     * 관리자가 1:1 문의 답변달기
     */
    @Override
    @Transactional
    public void insertQnaComment(QnaDto qnaDto) {
        System.out.println("===1:1 문의 답변 작성===");
        Qna qna = qnaRepo.getById(qnaDto.getId());
        qna.setComment(qnaDto.getComment());
        qnaRepo.save(qna);
    }
}
