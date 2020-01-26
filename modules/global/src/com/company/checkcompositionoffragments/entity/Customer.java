package com.company.checkcompositionoffragments.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@NamePattern("%s %s|firstName,lastName")
@Table(name = "CHECKCOMPOSITIONOFFRAGMENTS_CUSTOMER")
@Entity(name = "checkcompositionoffragments_Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = - 703066274293289105L;

    @Column(name = "FIRST_NAME")
    protected String firstName;

    @Column(name = "CUSTOMER_TYPE")
    protected String customerType;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DAY")
    protected Date birthDay;

    @Column(name = "LAST_NAME")
    protected String lastName;

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public CustomerType getCustomerType() {
        return customerType == null ? null : CustomerType.fromId(customerType);
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType == null ? null : customerType.getId();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}