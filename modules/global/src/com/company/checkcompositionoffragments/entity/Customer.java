package com.company.checkcompositionoffragments.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamePattern("%s|firstName")
@Table(name = "CHECKCOMPOSITIONOFFRAGMENTS_CUSTOMER")
@Entity(name = "checkcompositionoffragments_Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = - 703066274293289105L;

    @Column(name = "FIRST_NAME")
    protected String firstName;

    @Column(name = "LAST_NAME")
    protected String lastName;

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