package com.company.checkcompositionoffragments.web.screens.customer;

import com.haulmont.cuba.gui.screen.*;
import com.company.checkcompositionoffragments.entity.Customer;

@UiController("checkcompositionoffragments_Customer.edit")
@UiDescriptor("customer-edit.xml")
@EditedEntityContainer("customerDc")
@LoadDataBeforeShow
public class  CustomerEdit extends StandardEditor<Customer> {
}