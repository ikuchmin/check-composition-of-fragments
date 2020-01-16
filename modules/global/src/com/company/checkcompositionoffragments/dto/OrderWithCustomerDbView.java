package com.company.checkcompositionoffragments.dto;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.global.DesignSupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@DesignSupport("{'dbView':true,'generateDdl':false}")
@Table(name = "CHECKCOMPOSITIONOFFRAGMENTS_ORDER_WITH_CUSTOMER_VIEW")
@Entity(name = "checkcompositionoffragments_OrderWithCustomerDbView")
public class OrderWithCustomerDbView extends StandardEntity {
    private static final long serialVersionUID = 3215405508480587026L;

    @Column(name = "NUMBER_")
    protected String number;

    @Column(name = "CUSTOMER")
    protected String customer;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}