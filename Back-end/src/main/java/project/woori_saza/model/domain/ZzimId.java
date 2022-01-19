package project.woori_saza.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Embeddable
@NoArgsConstructor
public class ZzimId implements Serializable {

    private String profileId;
    private Long articleId;

}
