package com.company.checkcompositionoffragments.repository;

import com.company.checkcompositionoffragments.core.filter.Filter;
import com.company.checkcompositionoffragments.core.paging.Pageable;
import com.company.checkcompositionoffragments.core.filter.FilterSpecification;
import com.company.checkcompositionoffragments.core.filter.MathFilterOperation;
import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView;
import com.haulmont.cuba.core.global.Sort;

import java.io.Serializable;
import java.util.List;

public interface OrderWithCustomerRepositoryService {
    String NAME = "checkcompositionoffragments_OrderWithCustomerRepositoryService";

    List<OrderWithCustomerDbView> findAllOrderWithCustomers(Filter<OrderWithCustomerDbView> filter,
                                                            Sort sort, Pageable pageable);

    class OrderWithCustomerFilter extends Filter<OrderWithCustomerDbView> implements Serializable {

        public static ByCustomerNameContains customerNameContains(String customerNameFragment) {
            return new ByCustomerNameContains(customerNameFragment);
        }

        public static ByOrderNumberLessEqualsMore orderNumberLessEqualsMore(String orderNumber,
                                                                       MathFilterOperation operation) {

            return new ByOrderNumberLessEqualsMore(orderNumber, operation);
        }

        public static ByOrderNumberLessEqualsMore orderNumberLess(String orderNumber) {

            return new ByOrderNumberLessEqualsMore(orderNumber, MathFilterOperation.LESS);
        }

        public static ByOrderNumberLessEqualsMore orderNumberEqual(String orderNumber) {

            return new ByOrderNumberLessEqualsMore(orderNumber, MathFilterOperation.EQUAL);
        }

        public static ByOrderNumberLessEqualsMore orderNumberMore(String orderNumber) {

            return new ByOrderNumberLessEqualsMore(orderNumber, MathFilterOperation.MORE);
        }
    }

    class ByCustomerNameContains implements FilterSpecification<OrderWithCustomerDbView>, Serializable {

        private static final long serialVersionUID = - 8780371022898343506L;

        protected String customerNameFragment;

        private ByCustomerNameContains(String customerNameFragment) {
            this.customerNameFragment = customerNameFragment;
        }

        public String getCustomerNameFragment() {
            return customerNameFragment;
        }
    }

    class ByOrderNumberLessEqualsMore implements FilterSpecification<OrderWithCustomerDbView>, Serializable {

        private static final long serialVersionUID = 3848909956750358000L;

        protected String orderNumber;

        protected MathFilterOperation operation;

        private ByOrderNumberLessEqualsMore(String orderNumber, MathFilterOperation operation) {
            this.orderNumber = orderNumber;
            this.operation = operation;
        }
    }
}