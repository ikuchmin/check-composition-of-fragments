package com.company.checkcompositionoffragments.core.filter;

import com.haulmont.cuba.core.entity.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Filter<E extends Entity> implements Serializable {

    private static final long serialVersionUID = 5831398263768982529L;

    private List<FilterSpecification<E>> specifications = new ArrayList<>();

    public static <T extends Entity> Filter<T> nofilter() {
        return new Filter<T>();
    }

    public Filter<E> by(FilterSpecification<E> specification) {
        specifications.add(specification);

        return this;
    }

    public List<FilterSpecification<E>> getSpecifications() {
        return specifications;
    }
}
