package com.company.checkcompositionoffragments.core.subpackage;

import com.company.checkcompositionoffragments.core.A;
import com.haulmont.cuba.core.entity.StandardEntity;

public class E extends StandardEntity {
    protected A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
