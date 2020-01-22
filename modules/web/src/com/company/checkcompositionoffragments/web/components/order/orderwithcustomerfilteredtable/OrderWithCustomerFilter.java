package com.company.checkcompositionoffragments.web.components.order.orderwithcustomerfilteredtable;

import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView;
import com.company.checkcompositionoffragments.entity.Customer;
import com.company.checkcompositionoffragments.filter.Filter;
import com.haulmont.bali.events.Subscription;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.ScreenFragment;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.util.EventObject;
import java.util.function.Consumer;

import static com.company.checkcompositionoffragments.filter.orderwithcustomer.OrderWithCustomerFilters.customerNameContains;

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

        String customerName = ((Entity) customerFilterField.getValue()).getInstanceName();
        Filter<OrderWithCustomerDbView> filter = Filter.by(customerNameContains(customerName));

        fireEvent(ApplyFilterEvent.class, new ApplyFilterEvent(this, filter));
    }

    @SuppressWarnings("UnusedReturnValue")
    public Subscription addApplyFilterEventListener(Consumer<ApplyFilterEvent> listener) {
        return getEventHub().subscribe(ApplyFilterEvent.class, listener);
    }

    public static class ApplyFilterEvent extends EventObject {

        private static final long serialVersionUID = 7794049359708660354L;

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