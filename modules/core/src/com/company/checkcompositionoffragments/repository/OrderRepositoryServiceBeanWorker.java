package com.company.checkcompositionoffragments.repository;

import com.company.checkcompositionoffragments.entity.Order;
import com.haulmont.cuba.core.entity.contracts.Id;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderRepositoryServiceBeanWorker {

    private DataManager dataManager;

    public OrderRepositoryServiceBeanWorker(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public Order retrieveOrderById(Id<Order, UUID> orderId) {
        Order order = dataManager.create(Order.class);
        order.setId(orderId.getValue());
        order.setNumber("12");
        return order;
    }
}
