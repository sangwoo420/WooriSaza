package project.woori_saza.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "paid_form")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaidForm {

    @Id
    @GeneratedValue
    @Column(name="Paid_form_id")
    private Long id;

    private String pic;

    private int billingNo;

    private LocalDateTime deliveryDate;

    private LocalDateTime receiptDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

}
