package project.woori_saza.model.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Zzim{
        @EmbeddedId
        private ZzimId zzimId;

}
