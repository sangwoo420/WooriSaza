package project.woori_saza.model.service;

import project.woori_saza.model.domain.Party;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.PartyResponseDto;

import java.util.List;

public interface PartyService {

    public List<PartyResponseDto> getPartyList(String id);
    public String updateParty(Party party);
}
