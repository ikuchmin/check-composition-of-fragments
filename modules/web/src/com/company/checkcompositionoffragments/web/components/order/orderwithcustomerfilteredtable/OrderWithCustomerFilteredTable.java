package com.company.checkcompositionoffragments.web.components.order.orderwithcustomerfilteredtable;

import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView;
import com.company.checkcompositionoffragments.entity.Customer;
import com.company.checkcompositionoffragments.web.components.order.orderwithcustomertable.OrderWithCustomerTable;
import com.haulmont.cuba.gui.components.Fragment;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.ScreenFragment;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("checkcompositionoffragments_OrderWithCustomerDbViewFragment")
@UiDescriptor("order-with-customer-filtered-table.xml")
public class OrderWithCustomerFilteredTable extends ScreenFragment {


    protected CollectionContainer<Customer> paramCustomersDc;

    protected CollectionContainer<OrderWithCustomerDbView> paramOrdersDc;

    @Subscribe
    protected void onInit(Screen.InitEvent event) {
//        OrderWithCustomerTable orderWithCustomerTable =
//                fragments.create(this, OrderWithCustomerTable.class);
//        orderWithCustomerTable.setDc(ordersBrowseDc);
//        orderWithCustomerTable.setDl(ordersDl);
//
//        Fragment orderTableFragment = orderWithCustomerTable.getFragment();
//        orderTableBox.add(orderTableFragment);
//        orderTableBox.expand(orderTableFragment);
    }

    public void onSearch() {
    //    orderWithProductsDs.refresh();
    }


}