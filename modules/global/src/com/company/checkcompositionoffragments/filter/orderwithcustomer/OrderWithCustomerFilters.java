package com.company.checkcompositionoffragments.filter.orderwithcustomer;

import com.company.checkcompositionoffragments.filter.MathFilterOperation;

public final class OrderWithCustomerFilters {

    public static ByCustomerNameContains customerNameContains(String customerNameFragment) {
        return new ByCustomerNameContains(customerNameFragment);
    }

    public static ByOrderNumberLessEqualsMore orderNumberLessEqualsMore(String orderNumber,
                                                                        MathFilterOperation operation) {

        return new ByOrderNumberLessEqualsMore(orderNumber, operation);
    }

    public static ByOrderNumberLessEqualsMore orderNumberLess(String orderNumber) {

        return new ByOrderNumberLessEqualsMore(orderNumber, MathFilterOperation.LESS);
    }

    public static ByOrderNumberLessEqualsMore orderNumberEqual(String orderNumber) {

        return new ByOrderNumberLessEqualsMore(orderNumber, MathFilterOperation.EQUAL);
    }

    public static ByOrderNumberLessEqualsMore orderNumberMore(String orderNumber) {

        return new ByOrderNumberLessEqualsMore(orderNumber, MathFilterOperation.MORE);
    }
}
