package com.company.checkcompositionoffragments.web.components.order.orderextendtable;

import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView;
import com.company.checkcompositionoffragments.entity.Order;
import com.company.checkcompositionoffragments.web.screens.order.OrderEdit;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.HasLoader;
import com.haulmont.cuba.gui.screen.ScreenFragment;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("checkcompositionoffragments_OrderWithCustomerTable")
@UiDescriptor("order-with-customer-table.xml")
public class OrderWithCustomerTable extends ScreenFragment {

    @Inject
    protected Metadata metadata;

    @Inject
    protected Screens screens;

    @Inject
    protected GroupTable<OrderWithCustomerDbView> ordersTable;

    protected CollectionContainer<OrderWithCustomerDbView> dc;

    protected CollectionLoader<OrderWithCustomerDbView> dl;

    @Subscribe
    protected void onInit(InitEvent event) {
        getScreenData().registerContainer("ordersDc", dc);
    }

    @Subscribe("ordersTable.create")
    protected void onOrdersTableCreate(Action.ActionPerformedEvent event) {
        Order order = metadata.create(Order.class);

        OrderEdit orderEdit = screens.create(OrderEdit.class);
        orderEdit.setEntityToEdit(order);

        orderEdit.addAfterCloseListener(e -> ((HasLoader) dc).getLoader().load());
        orderEdit.show();
    }

    public CollectionContainer<OrderWithCustomerDbView> getDc() {
        return dc;
    }

    public void setDc(CollectionContainer<OrderWithCustomerDbView> dc) {
        this.dc = dc;
    }

    public CollectionLoader<OrderWithCustomerDbView> getDl() {
        return dl;
    }

    public void setDl(CollectionLoader<OrderWithCustomerDbView> dl) {
        this.dl = dl;
    }
}