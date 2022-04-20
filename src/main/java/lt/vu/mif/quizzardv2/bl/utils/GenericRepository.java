package lt.vu.mif.quizzardv2.bl.utils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public abstract class GenericRepository<T, ID> extends SimpleJpaRepository<T, ID> implements JpaSpecificationExecutor<T>, JpaRepository<T, ID> {

    protected EntityManager entityManager;

    public GenericRepository(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    protected NoResultException buildNoResultException(@NotNull String methodName) {
        return new NoResultException(String.format("'%s::%s' Row by provided parameters not found", this.getClass(), methodName));
    }

    protected NoResultException buildNoResultException(@NotNull String methodName, @NotNull ID id) {
        return new NoResultException(String.format("'%s::%s' Row by id '%s' not found", this.getClass(), methodName, id));
    }

    protected Specification<T> getPermissionSpecification() {
        return Specification.where(null);
    }

    public Optional<T> findOne(@NotNull ID id) {
        Specification<T> specification = getPermissionSpecification();
        specification = specification.and((root, query, cb) -> cb.equal(root.get("id"), id));
        return findOne(specification);
    }

    public <M> Page<M> findAll(Specification<T> spec, Pageable pageable, Function<? super T, ? extends M> mapper) {
        Specification<T> specification = getPermissionSpecification();
        specification = specification.and(spec);
        return findAll(specification, pageable).map(mapper);
    }

    public <M> Page<M> findAll(Pageable pageable, Function<? super T, ? extends M> mapper) {
        Specification<T> specification = getPermissionSpecification();
        return findAll(specification, pageable).map(mapper);
    }

    public <M> List<M> findAll(Specification<T> spec, Function<? super T, ? extends M> mapper) {
        Specification<T> specification = getPermissionSpecification();
        specification = specification.and(spec);
        return findAll(specification).stream().map(mapper).collect(Collectors.toList());
    }

    public <M> List<M> findAll(Specification<T> spec, Sort sort, Function<? super T, ? extends M> mapper) {
        Specification<T> specification = getPermissionSpecification();
        specification = specification.and(spec);
        return findAll(specification, sort).stream().map(mapper).collect(Collectors.toList());
    }

    public List<T> findAllByIdIn(Collection<ID> ids) {
        Specification<T> specification = getPermissionSpecification();
        specification = specification.and((root, query, cb) -> root.get("id").in(ids));
        return findAll(specification);
    }

    public T findByIdRequired(@NotNull ID id) {
        Specification<T> specification = getPermissionSpecification();
        specification = specification.and((root, query, cb) -> cb.equal(root.get("id"), id));
        return findOne(specification).orElseThrow(() -> buildNoResultException("findByIdRequired", id));
    }

    public <R> R findByIdRequired(@NotNull ID id, @NotNull Function<T, R> mapper) {
        Specification<T> specification = getPermissionSpecification();
        specification = specification.and((root, query, cb) -> cb.equal(root.get("id"), id));
        return findOne(specification).map(mapper).orElseThrow(() -> buildNoResultException("findByIdRequired", id));
    }

    public T findOneRequired(Specification<T> spec) {
        Specification<T> specification = getPermissionSpecification();
        specification = specification.and(spec);
        return findOne(specification).orElseThrow(() -> buildNoResultException("findOneRequired"));
    }

    public <R> Stream<R> findAllStream(@NotNull Function<T, R> mapper) {
        return findAll(getPermissionSpecification()).stream().map(mapper);
    }

}
