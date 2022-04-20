package lt.vu.mif.quizzardv2.model.gameInstanceQuestion.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.vu.mif.quizzardv2.model.question.entity.Question;
import lt.vu.mif.quizzardv2.model.gameInstanceQuestion.compositeKey.GameInstanceQuestionPK;
import lt.vu.mif.quizzardv2.model.gameInstance.entity.GameInstance;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "game_instance_question")
public class GameInstanceQuestion {

    @EmbeddedId
    private GameInstanceQuestionPK id;

    @MapsId("questionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @MapsId("gameInstanceId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_instance_id", nullable = false)
    private GameInstance gameInstance;

    @Column(name = "answered_correctly")
    private Boolean answeredCorrectly;
}