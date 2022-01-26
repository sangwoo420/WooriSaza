package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.PaidForm;
import project.woori_saza.model.dto.PaidFormRequestDto;
import project.woori_saza.model.dto.PaidFormResponseDto;
import project.woori_saza.model.repo.PaidFormRepo;
import project.woori_saza.model.repo.PartyRepo;

@Service
@Transactional(readOnly = true)
public class PaidFormServiceImpl implements PaidFormService{

    @Autowired
    PaidFormRepo paidFormRepo;

    @Autowired
    PartyRepo partyRepo;

    @Override
    public PaidFormResponseDto getPaidForm(Long paidFormId) {
        PaidForm paidForm = paidFormRepo.getById(paidFormId);
        PaidFormResponseDto paidFormResponseDto = new PaidFormResponseDto(paidForm);
        return paidFormResponseDto;
    }

    @Override
    @Transactional
    public void insertPaidForm(PaidFormRequestDto paidFormRequestDto) {
        PaidForm paidForm = paidFormRequestDto.toEntity();
        paidForm.setParty(partyRepo.getById(paidFormRequestDto.getParty()));
        paidFormRepo.save(paidForm);
    }

    @Override
    @Transactional
    public void updatePaidForm(Long paidFormId, PaidFormRequestDto paidFormRequestDto) {
        PaidForm paidForm = paidFormRepo.getById(paidFormId);
        paidForm.setPic(paidFormRequestDto.getPic());
        paidForm.setBillingNo(paidFormRequestDto.getBillingNo());
        paidForm.setDeliveryDate(paidFormRequestDto.getDeliveryDate());
        paidForm.setReceiptDate(paidFormRequestDto.getReceiptDate());
    }

    @Override
    @Transactional
    public void deletePaidForm(Long paidFormId) {
        paidFormRepo.deleteById(paidFormId);
    }
}
