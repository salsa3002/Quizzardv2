package lt.vu.mif.quizzardv2.bl.questionAnswer.repository;

import lt.vu.mif.quizzardv2.bl.utils.GenericRepository;
import lt.vu.mif.quizzardv2.model.question.entity.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class QuestionRepository extends GenericRepository<Question, Long> {

    QuestionRepository(EntityManager entityManager) {
        super(Question.class, entityManager);
    }
}
