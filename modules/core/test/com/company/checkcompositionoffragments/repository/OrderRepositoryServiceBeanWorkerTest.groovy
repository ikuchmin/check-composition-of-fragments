package com.company.checkcompositionoffragments.repository

import com.company.checkcompositionoffragments.core.CheckCompositionOfFragmentsIntegrationSpecification
import com.haulmont.cuba.core.entity.contracts.Id
import com.haulmont.cuba.core.global.AppBeans

class OrderRepositoryServiceBeanWorkerTest extends CheckCompositionOfFragmentsIntegrationSpecification {

    OrderRepositoryServiceBeanWorker orderRepositoryServiceBeanWorker

    @Override
    void setup() {
        orderRepositoryServiceBeanWorker = AppBeans.get(OrderRepositoryServiceBeanWorker)
    }

    def "check that stored order can be retrieved by id"() {
        given:
        def order = builder.Order {
            $$.number = 12
        }

        when:
        def fetchedOrder = orderRepositoryServiceBeanWorker.retrieveOrderById(Id.of(order))

        then:
        fetchedOrder == order
    }
}
