package project.woori_saza.model.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.woori_saza.model.repo.PartyRepo;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PartyTest {

    @Autowired
    PartyRepo partyRepo;

    @Test
    void insert(){
        Party party = new Party(1L,false,null,null,null);
        partyRepo.save(party);
    }

}