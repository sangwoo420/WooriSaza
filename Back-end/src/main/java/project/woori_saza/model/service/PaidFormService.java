package project.woori_saza.model.service;

import org.springframework.web.multipart.MultipartFile;
import project.woori_saza.model.dto.PaidFormRequestDto;
import project.woori_saza.model.dto.PaidFormResponseDto;

public interface PaidFormService {

    PaidFormResponseDto getPaidForm(Long paidFormId);
    void insertPaidForm(PaidFormRequestDto paidFormRequestDto);
    String upload(MultipartFile uploadFile) throws Exception;
    void updatePaidForm(Long paidFormId, PaidFormRequestDto paidFormRequestDto);
    void deletePaidForm(Long paidFormId);
}
