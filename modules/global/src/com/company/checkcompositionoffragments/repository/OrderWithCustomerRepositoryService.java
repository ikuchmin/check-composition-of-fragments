package com.company.checkcompositionoffragments.repository;

import com.company.checkcompositionoffragments.core.paging.Pageable;
import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView;
import com.company.checkcompositionoffragments.filter.Filter;
import com.haulmont.cuba.core.global.Sort;

import java.util.List;

public interface OrderWithCustomerRepositoryService {
    String NAME = "checkcompositionoffragments_OrderWithCustomerRepositoryService";

    List<OrderWithCustomerDbView> findAllOrderWithCustomers(Filter<OrderWithCustomerDbView> filter,
                                                            Sort sort, Pageable pageable);
}