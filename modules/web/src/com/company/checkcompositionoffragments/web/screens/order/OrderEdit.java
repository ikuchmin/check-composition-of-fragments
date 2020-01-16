package com.company.checkcompositionoffragments.web.screens.order;

import com.haulmont.cuba.gui.screen.*;
import com.company.checkcompositionoffragments.entity.Order;

@UiController("checkcompositionoffragments_Order.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
@LoadDataBeforeShow
public class OrderEdit extends StandardEditor<Order> {
}