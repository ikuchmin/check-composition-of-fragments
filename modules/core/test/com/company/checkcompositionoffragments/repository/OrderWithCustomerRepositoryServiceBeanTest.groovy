package com.company.checkcompositionoffragments.repository

import com.company.checkcompositionoffragments.core.CheckCompositionOfFragmentsIntegrationSpecification
import com.company.checkcompositionoffragments.core.paging.PageRequest
import com.company.checkcompositionoffragments.core.paging.Pageable
import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView
import com.company.checkcompositionoffragments.filter.Filter
import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.Sort
import com.haulmont.cuba.core.global.UuidProvider

import static com.company.checkcompositionoffragments.filter.orderwithcustomer.OrderWithCustomerFilters.customerNameContains

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

    def "check that user can filter result of available orders by customer name (contains)"() {
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
                $$.firstName = 'OutFirstName'
                $$.lastName = 'OutLastName'
            }
        }

        when:
        List<OrderWithCustomerDbView> fetched = delegate
                .findAllOrderWithCustomers(Filter.by(customerNameContains("Test")),
                        Sort.UNSORTED, Pageable.unpaged())

        then:
        order1.id in fetched.collect { it.id }
        ! (order2.id in fetched.collect { it.id })
    }

    def "check that user can sort list of orders by property"() {
        given:
        def predefinedCustomerName = String.valueOf(UuidProvider.createUuid())

        def order1 = builder.Order() {
            $$.number = String.valueOf(Math.random() * 100)
            $$.customer = Customer() {
                $$.firstName = predefinedCustomerName
                $$.lastName = 'TestLastName'
            }
        }

        def order2 = builder.Order() {
            $$.number = String.valueOf(Math.random() * 100)
            $$.customer = Customer() {
                $$.firstName = predefinedCustomerName
                $$.lastName = 'OutLastName'
            }
        }

        def order3 = builder.Order() {
            $$.number = String.valueOf(Math.random() * 100)
            $$.customer = Customer() {
                $$.firstName = predefinedCustomerName
                $$.lastName = 'OutLastName'
            }
        }

        when:
        List<OrderWithCustomerDbView> fetched = delegate
                .findAllOrderWithCustomers(Filter.by(customerNameContains(predefinedCustomerName)),
                        Sort.by(Sort.Direction.DESC, "createTs"), Pageable.unpaged())

        then:
        [order3.id, order2.id, order1.id] == fetched.collect { it.id }

    }

    // add sort
    def "check that user can get orders page by page"() {
        given:
        def predefinedCustomerName = String.valueOf(UuidProvider.createUuid())

        def order1 = builder.Order() {
            $$.number = String.valueOf(Math.random() * 100)
            $$.customer = Customer() {
                $$.firstName = predefinedCustomerName
                $$.lastName = 'TestLastName'
            }
        }

        def order2 = builder.Order() {
            $$.number = String.valueOf(Math.random() * 100)
            $$.customer = Customer() {
                $$.firstName = predefinedCustomerName
                $$.lastName = 'OutLastName'
            }
        }

        def order3 = builder.Order() {
            $$.number = String.valueOf(Math.random() * 100)
            $$.customer = Customer() {
                $$.firstName = predefinedCustomerName
                $$.lastName = 'OutLastName'
            }
        }

        when:
        List<OrderWithCustomerDbView> fetched = delegate
                .findAllOrderWithCustomers(Filter.by(customerNameContains(predefinedCustomerName)),
                        Sort.UNSORTED, PageRequest.of(0, 1))

        then:
        order1.id in fetched.collect { it.id }
        ! (order2.id in fetched.collect { it.id })
        ! (order3.id in fetched.collect { it.id })

        when:
        fetched = delegate
                .findAllOrderWithCustomers(Filter.by(customerNameContains(predefinedCustomerName)),
                        Sort.UNSORTED, PageRequest.of(1, 1))

        then:
        ! (order1.id in fetched.collect { it.id })
        order2.id in fetched.collect { it.id }
        ! (order3.id in fetched.collect { it.id })

        when:
        fetched = delegate
                .findAllOrderWithCustomers(Filter.by(customerNameContains(predefinedCustomerName)),
                        Sort.UNSORTED, PageRequest.of(2, 1))

        then:
        ! (order1.id in fetched.collect { it.id })
        ! (order2.id in fetched.collect { it.id })
        order3.id in fetched.collect { it.id }

    }
}
