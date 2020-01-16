package com.company.checkcompositionoffragments.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamePattern("%s|name")
@Table(name = "CHECKCOMPOSITIONOFFRAGMENTS_PRODUCT")
@Entity(name = "checkcompositionoffragments_Product")
public class Product extends StandardEntity {
    private static final long serialVersionUID = 7145835563295117898L;

    @Column(name = "NAME")
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}