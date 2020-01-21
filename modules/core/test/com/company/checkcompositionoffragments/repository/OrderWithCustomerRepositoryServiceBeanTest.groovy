package com.company.checkcompositionoffragments.repository

import com.company.checkcompositionoffragments.core.CheckCompositionOfFragmentsIntegrationSpecification
import com.company.checkcompositionoffragments.core.filter.Filter
import com.company.checkcompositionoffragments.core.paging.Pageable
import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView
import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.Sort

class OrderWithCustomerRepositoryServiceBeanTest extends CheckCompositionOfFragmentsIntegrationSpecification {

    OrderWithCustomerRepositoryService delegate

    @Override
    void setup() {

        delegate = AppBeans.get(OrderWithCustomerRepositoryService)
    }

    def "check that user can read all of available orders without filters, sorting, pagination"() {
        given:
        def order1 = builder.Order() {
            $$.number = String.valueOf(Math.random() * 100)
            $$.customer = Customer() {
                $$.firstName = 'TestFirstName'
                $$.lastName = 'TestLastName'
            }
        }

        def order2 = builder.Order() {
            $$.number = String.valueOf(Math.random() * 100)
            $$.customer = Customer() {
                $$.firstName = 'TestFirstName'
                $$.lastName = 'TestLastName'
            }
        }

        when:
        List<OrderWithCustomerDbView> fetched = delegate
                .findAllOrderWithCustomers(Filter.nofilter(), Sort.UNSORTED, Pageable.unpaged())

        then:
        order1.id in fetched.collect { it.id }
        order2.id in fetched.collect { it.id }
    }
}
