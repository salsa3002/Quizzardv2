package lt.vu.mif.quizzardv2.bl.questionAnswer.repository;

import lt.vu.mif.quizzardv2.bl.utils.GenericRepository;
import lt.vu.mif.quizzardv2.model.answer.entity.Answer;
import lt.vu.mif.quizzardv2.model.question.entity.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class AnswerRepository extends GenericRepository<Answer, Long> {

    AnswerRepository(EntityManager entityManager) {
        super(Answer.class, entityManager);
    }
}
