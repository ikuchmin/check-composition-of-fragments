package com.company.checkcompositionoffragments.web.components.order.orderwithcustomerfilteredtable;

import com.company.checkcompositionoffragments.core.filter.Filter;
import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView;
import com.company.checkcompositionoffragments.entity.Customer;
import com.company.checkcompositionoffragments.entity.Order;
import com.company.checkcompositionoffragments.repository.OrderWithCustomerRepositoryService;
import com.company.checkcompositionoffragments.web.components.order.orderwithcustomertable.OrderWithCustomerTable;
import com.haulmont.bali.events.Subscription;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.Fragment;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.ScreenFragment;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.util.EventObject;
import java.util.List;
import java.util.function.Consumer;

import static com.company.checkcompositionoffragments.repository.OrderWithCustomerRepositoryService.OrderWithCustomerFilter.customerNameContains;

@UiController("checkcompositionoffragments_OrderWithCustomerFilter")
@UiDescriptor("order-with-customer-filter.xml")
public class OrderWithCustomerFilter extends ScreenFragment {

    @Inject
    protected CollectionLoader<Customer> customerLoader;

    @Inject
    protected LookupField customerFilterField;

    @Subscribe
    protected void onInit(InitEvent event) {
        customerLoader.load();
    }



    public void onSearch() {

        Filter<OrderWithCustomerDbView> filter = new Filter<OrderWithCustomerDbView>()
                .by(customerNameContains(((Entity)customerFilterField.getValue()).getInstanceName()));

        fireEvent(ApplyFilterEvent.class, new ApplyFilterEvent(this, filter));
    }



    public Subscription addApplyFilterEventListener(Consumer<ApplyFilterEvent> listener) {
        return getEventHub().subscribe(ApplyFilterEvent.class, listener);
    }

    public static class ApplyFilterEvent extends EventObject {

        protected Filter<OrderWithCustomerDbView> filter;

        public ApplyFilterEvent(Object source, Filter<OrderWithCustomerDbView> filter) {
            super(source);

            this.filter = filter;
        }

        public Filter<OrderWithCustomerDbView> getFilter() {
            return filter;
        }
    }
}