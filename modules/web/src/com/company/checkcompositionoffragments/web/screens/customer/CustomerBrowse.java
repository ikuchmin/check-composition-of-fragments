package com.company.checkcompositionoffragments.web.screens.customer;

import com.haulmont.cuba.gui.screen.*;
import com.company.checkcompositionoffragments.entity.Customer;

@UiController("checkcompositionoffragments_Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
@LoadDataBeforeShow
public class CustomerBrowse extends StandardLookup<Customer> {
}