package lt.vu.mif.quizzardv2.bl.question.specification;

import lombok.experimental.UtilityClass;
import lt.vu.mif.quizzardv2.bl.question.json.QuestionSearch;
import lt.vu.mif.quizzardv2.bl.utils.CriteriaBuilderUtils;
import lt.vu.mif.quizzardv2.model.question.entity.Question;
import lt.vu.mif.quizzardv2.model.question.entity.Question_;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class QuestionSpecification {

    public Specification<Question> build(QuestionSearch search) {
        Specification<Question> specification = (root, query, cb) -> null;

        if (search.getId() != null) {
            specification = specification.and((root, query, cb) -> cb.equal(root.get(Question_.id), search.getId()));
        }

        if (StringUtils.isNotBlank(search.getSearchText())) {
            specification = specification.and(((root, query, cb) -> CriteriaBuilderUtils.likeWithNormalizedQuery(cb, root.get(Question_.question), search.getSearchText().trim())));
        }

        if (search.getCategory() != null) {
            specification = specification.and((root, query, cb) -> cb.equal(root.get(Question_.category), search.getCategory()));
        }

        return specification;
    }
}
