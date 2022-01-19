package project.woori_saza.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class ZzimId implements Serializable {

    private String profileId;
    private Long articleId;

}
