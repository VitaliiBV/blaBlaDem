package com.example.blabladem.specification;

import com.example.blabladem.domain.Task;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TaskSpecification implements Specification<Task> {
    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getKey().equalsIgnoreCase("departmentId") && criteria.getOperation().equalsIgnoreCase(":")) {
            return criteriaBuilder.equal(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        return null;
    }
}
