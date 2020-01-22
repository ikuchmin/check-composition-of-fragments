package com.company.checkcompositionoffragments.exception;

import com.company.checkcompositionoffragments.filter.FilterSpecification;

import java.util.List;

public class UnsupportedFilterException extends RuntimeException {
    private static final long serialVersionUID = 1858357876194578810L;

    List<? extends FilterSpecification> unsupportedFilterClasses;

    public UnsupportedFilterException(List<? extends FilterSpecification> unsupportedFilterClasses) {
        this.unsupportedFilterClasses = unsupportedFilterClasses;
    }

    public UnsupportedFilterException(String message, List<? extends FilterSpecification> unsupportedFilterClasses) {
        super(message);
        this.unsupportedFilterClasses = unsupportedFilterClasses;
    }

    public UnsupportedFilterException(String message, Throwable cause, List<? extends FilterSpecification> unsupportedFilterClasses) {
        super(message, cause);
        this.unsupportedFilterClasses = unsupportedFilterClasses;
    }

    public UnsupportedFilterException(Throwable cause, List<? extends FilterSpecification> unsupportedFilterClasses) {
        super(cause);
        this.unsupportedFilterClasses = unsupportedFilterClasses;
    }
}
