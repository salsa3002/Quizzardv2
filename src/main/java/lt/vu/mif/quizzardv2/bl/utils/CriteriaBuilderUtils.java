package lt.vu.mif.quizzardv2.bl.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@UtilityClass
public class CriteriaBuilderUtils {

    public <T> Page<T> getPage(TypedQuery<T> typedQuery, PageRequest pageRequest, long total) {
        typedQuery.setFirstResult((int) pageRequest.getOffset());
        typedQuery.setMaxResults(pageRequest.getPageSize());

        return new PageImpl<>(total > pageRequest.getOffset() ? typedQuery.getResultList() : Collections.emptyList(), pageRequest, total);
    }

    public <X, Y> Join<X, Y> getOrCreateJoin(From<?, X> from, Attribute<? super X, Y> attribute) {
        return getOrCreateJoin(from, attribute, JoinType.INNER);
    }

    @SuppressWarnings("unchecked")
    public <X, Y> Join<X, Y> getOrCreateJoin(@NotNull From<?, X> from, @NotNull Attribute<? super X, Y> attribute, @NotNull JoinType joinType) {
        // @formatter:off
        return from.getJoins().stream()
                .filter(join -> attribute.getName().equals(join.getAttribute().getName()) && join.getJoinType().equals(joinType))
                .findAny()
                .map(xJoin -> (Join<X, Y>) xJoin)
                .orElseGet(() -> from.join(attribute.getName(), joinType));
        // @formatter:on
    }

    public <X, Y> SetJoin<X, Y> getOrCreateJoin(From<?, X> from, SetAttribute<? super X, Y> attribute) {
        return getOrCreateJoin(from, attribute, JoinType.INNER);
    }

    @SuppressWarnings("unchecked")
    public <X, Y> SetJoin<X, Y> getOrCreateJoin(@NotNull From<?, X> from, @NotNull SetAttribute<? super X, Y> attribute, @NotNull JoinType joinType) {
        // @formatter:off
        return from.getJoins().stream()
                .filter(join -> attribute.getName().equals(join.getAttribute().getName()) && join.getJoinType().equals(joinType))
                .findAny()
                .map(xJoin -> (SetJoin<X, Y>) xJoin)
                .orElseGet(() -> from.joinSet(attribute.getName()));
        // @formatter:on
    }

    public <X, Y> ListJoin<X, Y> getOrCreateJoin(From<?, X> from, ListAttribute<? super X, Y> attribute) {
        return getOrCreateJoin(from, attribute, JoinType.INNER);
    }

    @SuppressWarnings("unchecked")
    public <X, Y> ListJoin<X, Y> getOrCreateJoin(@NotNull From<?, X> from, @NotNull ListAttribute<? super X, Y> attribute, @NotNull JoinType joinType) {
        // @formatter:off
        return from.getJoins().stream()
                .filter(join -> attribute.getName().equals(join.getAttribute().getName()) && join.getJoinType().equals(joinType))
                .findAny()
                .map(xJoin -> (ListJoin<X, Y>) xJoin)
                .orElseGet(() -> from.joinList(attribute.getName(), joinType));
        // @formatter:on
    }

    public String escapeWildcards(String string) {
        return string.replace("%", "\\%").replace("_", "\\_").replace("\\", "\\\\");
    }

    public <T> T getSingleResult(TypedQuery<T> query) {
        List<T> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        if (resultList.size() > 1) {
            throw new IllegalStateException("More than one ( " + resultList.size() + " ) result found!");
        }
        return resultList.get(0);
    }

    public String surroundLikeAndLowercase(String param) {
        return "%" + escapeWildcards(param).toLowerCase(Locale.ROOT) + "%";
    }

    public String endLikeAndLowercase(String param) {
        return escapeWildcards(param).toLowerCase(Locale.ROOT) + "%";
    }

    public Predicate endLike(CriteriaBuilder cb, Expression<String> expression, String param) {
        return cb.like(cb.lower(expression), endLikeAndLowercase(param));
    }
    public Predicate like(CriteriaBuilder cb, Expression<String> expression, String param) {
        return cb.like(cb.lower(expression), surroundLikeAndLowercase(param));
    }

    public Predicate likeWithNormalizedQuery(CriteriaBuilder cb, Expression<String> expression, String param) {
        return likeWithNormalizedQuery(cb, expression, param, false);
    }

    public Predicate likeWithNormalizedQuery(CriteriaBuilder cb, Expression<String> expression, String param, boolean email) {
        String normalizedParam = email ? NormalizeStringConverter.normalizeEmail(param) : NormalizeStringConverter.normalize(param);
        return cb.like(expression, surroundLikeAndLowercase(normalizedParam));
    }

    public Predicate endLikeWithNormalizedQuery(CriteriaBuilder cb, Expression<String> expression, String param) {
        String normalizedParam = NormalizeStringConverter.normalize(param);
        return cb.like(expression, endLikeAndLowercase(normalizedParam));
    }
}