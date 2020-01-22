package com.company.checkcompositionoffragments.filter.orderwithcustomer;

import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView;
import com.company.checkcompositionoffragments.filter.FilterSpecification;
import com.company.checkcompositionoffragments.filter.MathFilterOperation;

import java.io.Serializable;

public class ByOrderNumberLessEqualsMore implements FilterSpecification<OrderWithCustomerDbView>, Serializable {

    private static final long serialVersionUID = 3848909956750358000L;

    protected String orderNumber;

    protected MathFilterOperation operation;

    public ByOrderNumberLessEqualsMore(String orderNumber, MathFilterOperation operation) {
        this.orderNumber = orderNumber;
        this.operation = operation;
    }
}