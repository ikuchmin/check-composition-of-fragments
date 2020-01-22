package com.company.checkcompositionoffragments.filter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class Filter<T> implements Serializable {

    private static final long serialVersionUID = 5831398263768982529L;

    private final List<FilterSpecification<T>> specifications;

    public Filter(List<FilterSpecification<T>> specifications) {
        this.specifications = specifications;
    }

    public static <T> Filter<T> nofilter() {
        return new Filter<T>(Collections.emptyList());
    }

    public static <T> Filter<T> by(FilterSpecification<T> specification) {

        return new Filter<>(singletonList(specification));
    }

    public static <T> Filter<T> by(List<FilterSpecification<T>> specifications) {

        return new Filter<T>(specifications);
    }

    @SafeVarargs
    public static <T> Filter<T> by(FilterSpecification<T>... specification) {

        return new Filter<>(asList(specification));
    }

    public List<FilterSpecification<T>> getSpecifications() {
        return specifications;
    }
}
