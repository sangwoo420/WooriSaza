package project.woori_saza.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaidForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Paid_form_id")
    private Long id;

    private String pic;

    private Integer billingNo;

    private LocalDate deliveryDate;

    private LocalDate receiptDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    @Builder
    public PaidForm(String pic, Integer billingNo, LocalDate deliveryDate, LocalDate receiptDate) {
        this.pic = pic;
        this.billingNo = billingNo;
        this.deliveryDate = deliveryDate;
        this.receiptDate = receiptDate;
    }
}
