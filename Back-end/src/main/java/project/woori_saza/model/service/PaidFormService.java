package project.woori_saza.model.service;

import project.woori_saza.model.dto.PaidFormRequestDto;
import project.woori_saza.model.dto.PaidFormResponseDto;

public interface PaidFormService {

    PaidFormResponseDto getPaidForm(Long paidFormId);
    void insertPaidForm(PaidFormRequestDto paidFormRequestDto);
    void updatePaidForm(Long paidFormId, PaidFormRequestDto paidFormRequestDto);
    void deletePaidForm(Long paidFormId);
}
