package com.company.checkcompositionoffragments.web.components.order.orderwithcustomertable;

import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView;
import com.company.checkcompositionoffragments.entity.Order;
import com.company.checkcompositionoffragments.web.screens.order.OrderEdit;
import com.haulmont.bali.events.Subscription;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.data.table.ContainerGroupTableItems;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.ScreenFragment;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.util.EventObject;
import java.util.List;
import java.util.function.Consumer;

import static com.company.checkcompositionoffragments.web.components.order.orderwithcustomertable.OrderWithCustomerTable.ChangeListEntitiesEvent.ChangeEventType.CREATE;
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
                .withAfterCloseListener(e -> fireEvent(ChangeListEntitiesEvent.class,
                        new ChangeListEntitiesEvent(this, CREATE, asList(newOrder))))
                .newEntity(newOrder)
                .show();
    }

    public Subscription addChangeListEntitiesEventListener(Consumer<ChangeListEntitiesEvent> listener) {
        return getEventHub().subscribe(ChangeListEntitiesEvent.class, listener);
    }

    public CollectionContainer<OrderWithCustomerDbView> getDc() {
        return dc;
    }

    public void setDc(CollectionContainer<OrderWithCustomerDbView> dc) {
        this.dc = dc;
    }

    public static class ChangeListEntitiesEvent extends EventObject {

        enum ChangeEventType {
            CREATE, MODIFY, REMOVE
        }

        protected ChangeEventType eventType;

        protected List<Order> affectedItems;

        public ChangeListEntitiesEvent(Object source, ChangeEventType eventType,
                                       List<Order> affectedItems) {
            super(source);

            this.eventType = eventType;
            this.affectedItems = affectedItems;
        }

        public ChangeEventType getEventType() {
            return eventType;
        }

        public List<Order> getAffectedItems() {
            return affectedItems;
        }
    }
}