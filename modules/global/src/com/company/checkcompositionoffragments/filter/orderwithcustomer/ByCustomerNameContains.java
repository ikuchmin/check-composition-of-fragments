package com.company.checkcompositionoffragments.filter.orderwithcustomer;

import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView;
import com.company.checkcompositionoffragments.filter.FilterSpecification;

import java.io.Serializable;

public class ByCustomerNameContains implements FilterSpecification<OrderWithCustomerDbView>, Serializable {

    private static final long serialVersionUID = - 8780371022898343506L;

    protected String customerNameFragment;

    public ByCustomerNameContains(String customerNameFragment) {
        this.customerNameFragment = customerNameFragment;
    }

    public String getCustomerNameFragment() {
        return customerNameFragment;
    }
}

