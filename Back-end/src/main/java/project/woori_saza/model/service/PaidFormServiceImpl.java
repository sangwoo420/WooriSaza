package project.woori_saza.model.service;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.woori_saza.model.domain.PaidForm;
import project.woori_saza.model.dto.PaidFormRequestDto;
import project.woori_saza.model.dto.PaidFormResponseDto;
import project.woori_saza.model.repo.PaidFormRepo;
import project.woori_saza.model.repo.PartyRepo;

import java.io.File;
import java.util.UUID;

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
    public void insertPaidForm(PaidFormRequestDto paidFormRequestDto,MultipartFile multipartFile) throws Exception {

            File file=new File("");

            String originFilename = multipartFile.getOriginalFilename();
            String extension = originFilename.substring(originFilename.length()-3);

            if(!(extension.equals("jpg") || extension.equals("png"))){
                throw new FileUploadException("파일 확장자가 jpg나 png가 아닙니다.");
            }
            //파일이름 랜덤으로 만들기
            String saveFileName = UUID.randomUUID().toString() + originFilename.substring(originFilename.lastIndexOf(".")); //랜덤이름+확장자
            System.out.println("랜덤이름 출력"+saveFileName);

            PaidForm paidForm = paidFormRequestDto.toEntity();
            paidForm.setPic(saveFileName);
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
