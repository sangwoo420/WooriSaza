package project.woori_saza.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "paid_form")
@Getter
@Setter
public class PaidForm {

    @Id
    @GeneratedValue
    @Column(name="Paid_form_id")
    private Long id;

    private String pic;

    private int billingNo;

    private LocalDateTime deliveryDate;

    private LocalDateTime receiptDate;

    @OneToOne
    @JoinColumn(name = "party_id")
    private Party party;

}
