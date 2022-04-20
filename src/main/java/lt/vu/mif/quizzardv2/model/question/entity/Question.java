package lt.vu.mif.quizzardv2.model.question.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.vu.mif.quizzardv2.model.question.enums.CategoryEnum;
import lt.vu.mif.quizzardv2.model.answer.entity.Answer;
import lt.vu.mif.quizzardv2.model.gameInstanceQuestion.entity.GameInstanceQuestion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "question", length = 200)
    private String question;

    @Column(name = "category", length = 50)
    private CategoryEnum category;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "question")
    private Set<GameInstanceQuestion> gameInstanceQuestions = new LinkedHashSet<>();
}