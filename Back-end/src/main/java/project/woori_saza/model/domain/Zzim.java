package project.woori_saza.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Zzim{
        @EmbeddedId
        private ZzimId zzimId;

        @ManyToOne(fetch = LAZY)
        @MapsId("profileId")
        private UserProfile userProfile;

        @ManyToOne(fetch = LAZY)
        @MapsId("articleId")
        private Article article;


}
