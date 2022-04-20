package lt.vu.mif.quizzardv2.model.gameInstanceQuestion.compositeKey;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode
@Embeddable
public class GameInstanceQuestionPK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1996594755051899952L;

    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Column(name = "game_instance_id", nullable = false)
    private Long gameInstanceId;
}