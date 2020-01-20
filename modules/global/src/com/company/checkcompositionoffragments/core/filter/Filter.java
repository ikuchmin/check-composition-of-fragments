package com.company.checkcompositionoffragments.core.filter;

import com.haulmont.cuba.core.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Filter<E extends Entity> {

    private List<FilterSpecification<E>> specifications = new ArrayList<>();

    public Filter<E> by(FilterSpecification<E> specification) {
        specifications.add(specification);

        return this;
    }

    public List<FilterSpecification<E>> getSpecifications() {
        return specifications;
    }
}
