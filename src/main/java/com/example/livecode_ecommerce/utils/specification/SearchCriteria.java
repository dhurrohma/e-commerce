package com.example.livecode_ecommerce.utils.specification;

import com.example.livecode_ecommerce.utils.constants.FindOperator;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SearchCriteria {
    private String key;
    private FindOperator operator;
    private String value;

    public SearchCriteria(String key, FindOperator operator, String value) {
        this.key = key;
        this.operator = operator;
        this.value = value;
    }
}
