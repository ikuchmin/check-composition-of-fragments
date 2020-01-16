package com.company.checkcompositionoffragments.web.screens.orderitem;

import com.haulmont.cuba.gui.screen.*;
import com.company.checkcompositionoffragments.entity.OrderItem;

@UiController("checkcompositionoffragments_OrderItem.edit")
@UiDescriptor("order-item-edit.xml")
@EditedEntityContainer("orderItemDc")
@LoadDataBeforeShow
public class OrderItemEdit extends StandardEditor<OrderItem> {
}