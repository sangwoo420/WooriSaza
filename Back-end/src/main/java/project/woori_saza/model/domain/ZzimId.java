package project.woori_saza.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Embeddable
@NoArgsConstructor
public class ZzimId implements Serializable {
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "profile_id")
    private UserProfile userprofile;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

}
