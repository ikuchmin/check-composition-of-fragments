package com.company.checkcompositionoffragments.repository;

import com.company.checkcompositionoffragments.core.paging.Pageable;
import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView;
import com.company.checkcompositionoffragments.dto.QOrderWithCustomerDbView;
import com.company.checkcompositionoffragments.entity.QCustomer;
import com.company.checkcompositionoffragments.exception.UnsupportedFilterException;
import com.company.checkcompositionoffragments.filter.Filter;
import com.company.checkcompositionoffragments.filter.FilterSpecification;
import com.company.checkcompositionoffragments.filter.orderwithcustomer.ByCustomerNameContains;
import com.company.checkcompositionoffragments.querydsl.jpa.cuba.CubaQueryFactory;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.global.Sort;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;
import static java.util.stream.Collectors.toList;

@Service(OrderWithCustomerRepositoryService.NAME)
public class OrderWithCustomerRepositoryServiceBean implements OrderWithCustomerRepositoryService {

    private Logger log = LoggerFactory.getLogger(OrderWithCustomerRepositoryServiceBean.class);

    @Inject
    protected TransactionalDataManager txDm;

    @Override
    public List<OrderWithCustomerDbView> findAllOrderWithCustomers(Filter<OrderWithCustomerDbView> filter,
                                                                   Sort sort, Pageable pageable) {

        checkFilterIsSupported(filter, findAllOrderWithCustomersSupportedFilters());


        JPAQueryFactory queryFactory = new CubaQueryFactory(txDm);
        QCustomer customer = QCustomer.customer;

        QOrderWithCustomerDbView orderWithCustomer = QOrderWithCustomerDbView.orderWithCustomerDbView;

        var orderWithCustomerQuery = queryFactory.selectFrom(orderWithCustomer);

        // apply filters
        for (FilterSpecification<OrderWithCustomerDbView> spec : filter.getSpecifications()) {

            // effectively final trick
            var finalOrderWithCustomerQuery = orderWithCustomerQuery;
            orderWithCustomerQuery = Match(spec).of(
                    Case($(instanceOf(ByCustomerNameContains.class)),
                            f -> applyByCustomerNameContainsFilter(finalOrderWithCustomerQuery, f)));
        }

        log.debug("Query with filters: {}", orderWithCustomerQuery);

        // apply sort
        if (hasSort(sort)) {
            orderWithCustomerQuery = applySort(orderWithCustomerQuery, sort);
        }

        var orderWithCustomerDbViews = orderWithCustomerQuery.fetch();

        // apply pagination
//        ByQuery<OrderWithCustomerDbView, UUID> dmQueryWithPagination = dmQuery;
//        if (pageable != Pageable.unpaged()) {
//            dmQueryWithPagination = dmQueryWithPagination
//                    .firstResult((int) pageable.getOffset())
//                    .maxResults(pageable.getPageSize());
//        }

        return orderWithCustomerDbViews;
    }

    private boolean hasSort(Sort sort) {
        return ! sort.getOrders().isEmpty();
    }

    protected JPAQuery<OrderWithCustomerDbView> applySort(JPAQuery<OrderWithCustomerDbView> query, Sort sort) {

//        query.orderBy()
//        String orderLine = sort.getOrders().stream()
//                .map(o -> "o." + o.getProperty() + " " + o.getDirection())
//                .collect(Collectors.joining(", "));
//
//        return query + " order by " + orderLine;

        return query;
    }

    private boolean hasNoOrderClause(String query) {
        return query.toLowerCase().contains("order by");
    }

    private void checkFilterIsSupported(Filter<OrderWithCustomerDbView> filter,
                                        List<Class> supportedFilters) {

        List<FilterSpecification<OrderWithCustomerDbView>> unsupportedFilters =
                filter.getSpecifications().stream()
                        .filter(f -> ! supportedFilters.contains(f.getClass()))
                        .collect(toList());

        if (! unsupportedFilters.isEmpty()) {
            throw new UnsupportedFilterException(unsupportedFilters);
        }
    }

    @SuppressWarnings("rawtypes")
    private List<Class> findAllOrderWithCustomersSupportedFilters() {
        return Arrays.asList(ByCustomerNameContains.class);
    }

    private JPAQuery<OrderWithCustomerDbView> applyByCustomerNameContainsFilter(JPAQuery<OrderWithCustomerDbView> query,
                                                                                ByCustomerNameContains filter) {

        var orderWithCustomer = QOrderWithCustomerDbView.orderWithCustomerDbView;

        return query.where(orderWithCustomer.customer.like(filter.getCustomerNameFragment()));
    }

    private boolean hasNoWhereClause(String newQuery) {
        return ! newQuery.toLowerCase().contains("where");
    }
}