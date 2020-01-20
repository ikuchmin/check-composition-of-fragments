package com.company.checkcompositionoffragments.web.components.order.orderwithcustomertable;

import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView;
import com.company.checkcompositionoffragments.entity.Order;
import com.company.checkcompositionoffragments.web.screens.order.OrderEdit;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.data.table.ContainerGroupTableItems;
import com.haulmont.cuba.gui.components.data.table.ContainerTableItems;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.HasLoader;
import com.haulmont.cuba.gui.screen.ScreenFragment;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.List;

import static com.company.checkcompositionoffragments.web.components.order.orderwithcustomertable.OrderWithCustomerTable.ChangeListObjectsEvent.ChangeListEventType.CREATE;
import static java.util.Arrays.asList;

@UiController("checkcompositionoffragments_OrderWithCustomerTable")
@UiDescriptor("order-with-customer-table.xml")
public class OrderWithCustomerTable extends ScreenFragment {

    @Inject
    protected Metadata metadata;

    @Inject
    protected ScreenBuilders screenBuilders;

    @Inject
    protected GroupTable<OrderWithCustomerDbView> ordersTable;

    protected CollectionContainer<OrderWithCustomerDbView> dc;

    @Subscribe
    protected void onInit(InitEvent event) {
        ordersTable.setItems(new ContainerGroupTableItems<>(dc));
    }

    @Subscribe("ordersTable.create")
    protected void onOrdersTableCreate(Action.ActionPerformedEvent event) {
        Order newOrder = metadata.create(Order.class);

        screenBuilders.editor(Order.class, this)
                .withScreenClass(OrderEdit.class)
                .withAfterCloseListener(e -> new ChangeListObjectsEvent(this, CREATE, asList(newOrder)))
                .newEntity(newOrder)
                .show();
    }

    public CollectionContainer<OrderWithCustomerDbView> getDc() {
        return dc;
    }

    public void setDc(CollectionContainer<OrderWithCustomerDbView> dc) {
        this.dc = dc;
    }

    public static class ChangeListObjectsEvent extends EventObject {

        enum ChangeListEventType {
            CREATE, MODIFY, REMOVE
        }

        protected ChangeListEventType eventType;

        protected List<Order> affectedItems;

        public ChangeListObjectsEvent(Object source, ChangeListEventType eventType,
                                      List<Order> affectedItems) {
            super(source);
            this.eventType = eventType;
            this.affectedItems = affectedItems;
        }

        public ChangeListEventType getEventType() {
            return eventType;
        }

        public List<Order> getAffectedItems() {
            return affectedItems;
        }
    }
}