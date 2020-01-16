package com.company.checkcompositionoffragments.web.screens.order;

import com.haulmont.cuba.gui.screen.*;
import com.company.checkcompositionoffragments.entity.Order;

@UiController("checkcompositionoffragments_Order.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
@LoadDataBeforeShow
public class OrderBrowse extends StandardLookup<Order> {
}