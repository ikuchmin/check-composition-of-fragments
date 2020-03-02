package com.company.checkcompositionoffragments.repository;

import com.company.checkcompositionoffragments.entity.Order;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.entity.contracts.Id;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service(OrderRepositoryService.NAME)
public class OrderRepositoryServiceBean implements OrderRepositoryService {

    private TransactionalDataManager txDm;

    public OrderRepositoryServiceBean(TransactionalDataManager txDm) {
        this.txDm = txDm;
    }

    public void retrieveOrderById(Id<Order, UUID> orderId) {
        txDm.load()
    }
}
