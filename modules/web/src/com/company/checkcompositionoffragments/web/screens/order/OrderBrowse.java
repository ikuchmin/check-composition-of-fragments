package com.company.checkcompositionoffragments.web.screens.order;

import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView;
import com.company.checkcompositionoffragments.web.components.order.orderwithcustomertable.OrderWithCustomerTable;
import com.haulmont.cuba.gui.Fragments;
import com.haulmont.cuba.gui.components.Fragment;
import com.haulmont.cuba.gui.components.GroupBoxLayout;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.checkcompositionoffragments.entity.Order;

import javax.inject.Inject;

@UiController("checkcompositionoffragments_Order.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
@LoadDataBeforeShow
public class OrderBrowse extends StandardLookup<Order> {

    @Inject
    protected Fragments fragments;

    @Inject
    protected CollectionContainer<OrderWithCustomerDbView> ordersBrowseDc;

    @Inject
    protected GroupBoxLayout orderTableBox;

    @Inject
    protected CollectionLoader<OrderWithCustomerDbView> ordersDl;

    @Subscribe
    protected void onInit(InitEvent event) {
        OrderWithCustomerTable orderWithCustomerTable =
                fragments.create(this, OrderWithCustomerTable.class);
        orderWithCustomerTable.setDc(ordersBrowseDc);
        orderWithCustomerTable.setDl(ordersDl);

        Fragment orderTableFragment = orderWithCustomerTable.getFragment();
        orderTableBox.add(orderTableFragment);
        orderTableBox.expand(orderTableFragment);
    }


}