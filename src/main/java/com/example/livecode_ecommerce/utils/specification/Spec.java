package com.example.livecode_ecommerce.utils.specification;

import com.example.livecode_ecommerce.model.entity.Transaction;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class Spec<T> {
    public Specification<T> findBy(SearchCriteria searchCriteria) {
        switch (searchCriteria.getOperator()) {
            case LIKE:
                return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%"));
            case EQUAL:
                return (((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue())));
            case NOT_EQUAL:
                return ((root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(searchCriteria.getKey()), searchCriteria.getValue()));
            case JOIN_EQUAL:
                return ((root, query, criteriaBuilder) -> {
                    Join<T, Transaction> transactionJoin = root.join(searchCriteria.getKey());
                    return criteriaBuilder.equal(transactionJoin.get(searchCriteria.getKey() + "Id"), searchCriteria.getValue());
                });
            default:
                throw new RuntimeException("Operator not supported");
        }
    }
}
