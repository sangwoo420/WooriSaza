package project.woori_saza.model.service;

import project.woori_saza.model.domain.Qna;
import project.woori_saza.model.dto.QnaDto;

import java.util.List;

public interface QnaService {

    List<QnaDto> getMyQnaList(String profileId);
    List<QnaDto> getQnaList();
    QnaDto getQnaDetail(Long qnaId);
    void insertQna(QnaDto qnaDto);
    void updateQna(QnaDto qnaDto);
    void deleteQna(Long qnaId);
    void insertQnaComment(QnaDto qnaDto);
}
