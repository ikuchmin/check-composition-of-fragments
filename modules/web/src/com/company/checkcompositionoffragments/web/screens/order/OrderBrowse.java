package com.company.checkcompositionoffragments.web.screens.order;

import com.company.checkcompositionoffragments.core.paging.Pageable;
import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView;
import com.company.checkcompositionoffragments.repository.OrderRepositoryService;
import com.company.checkcompositionoffragments.repository.OrderWithCustomerRepositoryService;
import com.company.checkcompositionoffragments.repository.OrderWithCustomerRepositoryService.OrderWithCustomerFilter;
import com.company.checkcompositionoffragments.web.components.order.orderwithcustomertable.OrderWithCustomerTable;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Sort;
import com.haulmont.cuba.gui.Fragments;
import com.haulmont.cuba.gui.components.Fragment;
import com.haulmont.cuba.gui.components.GroupBoxLayout;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.checkcompositionoffragments.entity.Order;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@UiController("checkcompositionoffragments_Order.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
@LoadDataBeforeShow
public class OrderBrowse extends StandardLookup<Order> {

    @Inject
    protected Fragments fragments;

    @Inject
    protected OrderWithCustomerRepositoryService orderWithCustomerRepository;

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

        Fragment orderTableFragment = orderWithCustomerTable.getFragment();
        orderTableBox.add(orderTableFragment);
        orderTableBox.expand(orderTableFragment);
    }

    @Install(to = "ordersDl", target = Target.DATA_LOADER)
    protected List<OrderWithCustomerDbView> ordersDlLoadDelegate(
            LoadContext<OrderWithCustomerDbView> loadContext) {

        return orderWithCustomerRepository.findAllOrderWithCustomers(
                new OrderWithCustomerFilter(), Sort.UNSORTED, Pageable.unpaged());
    }



}