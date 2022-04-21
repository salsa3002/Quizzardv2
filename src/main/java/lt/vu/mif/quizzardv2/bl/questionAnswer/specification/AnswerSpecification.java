package lt.vu.mif.quizzardv2.bl.questionAnswer.specification;

import lombok.experimental.UtilityClass;
import lt.vu.mif.quizzardv2.model.answer.entity.Answer;
import lt.vu.mif.quizzardv2.model.answer.entity.Answer_;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class AnswerSpecification {

    public Specification<Answer> build(Long questionId) {

        Specification<Answer> specification = (root, query, cb) -> null;

        if (questionId != null) {
            specification = specification.and((root, query, cb) -> cb.equal(root.get(Answer_.questionId), questionId));
        }

        return specification;
    }
}
