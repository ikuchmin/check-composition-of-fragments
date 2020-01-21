package com.company.checkcompositionoffragments.repository;

import com.company.checkcompositionoffragments.core.filter.Filter;
import com.company.checkcompositionoffragments.core.filter.FilterSpecification;
import com.company.checkcompositionoffragments.core.paging.Pageable;
import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView;
import com.company.checkcompositionoffragments.exception.UnsupportedFilterException;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.global.FluentLoader;
import com.haulmont.cuba.core.global.FluentLoader.ByQuery;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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


        String baseQuery = "select o from checkcompositionoffragments_OrderWithCustomerDbView o";

        // apply filters
        String queryWithFilters = baseQuery;
        for (FilterSpecification<OrderWithCustomerDbView> spec : filter.getSpecifications()) {

            // effectively final trick
            String finalQueryWithFilters = queryWithFilters;

            queryWithFilters = Match(spec).of(
                    Case($(instanceOf(ByCustomerNameContains.class)),
                            f -> applyByCustomerNameContainsFilter(finalQueryWithFilters, f)));
        }

        log.debug("Query with filters: {}", queryWithFilters);

        // apply sort
        String queryWithSort = queryWithFilters;
        if (hasSort(sort)) {
            queryWithSort = applySort(queryWithSort, sort);
        }

        ByQuery<OrderWithCustomerDbView, UUID> dmQuery =
                txDm.load(OrderWithCustomerDbView.class).query(queryWithSort);

        // apply pagination
        ByQuery<OrderWithCustomerDbView, UUID> dmQueryWithPagination = dmQuery;
        if (pageable != Pageable.unpaged()) {
            dmQueryWithPagination = dmQueryWithPagination
                    .firstResult((int) pageable.getOffset())
                    .maxResults(pageable.getPageSize());
        }

        return dmQueryWithPagination.list();
    }

    private boolean hasSort(Sort sort) {
        return ! sort.getOrders().isEmpty();
    }

    protected String applySort(String query, Sort sort) {

        String orderLine = sort.getOrders().stream()
                .map(o -> "o." + o.getProperty() + " " + o.getDirection())
                .collect(Collectors.joining(", "));

        return query + " order by " + orderLine;
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

    private String applyByCustomerNameContainsFilter(String query, ByCustomerNameContains filter) {

        String newQuery = query;
        if (hasNoWhereClause(newQuery)) {
            newQuery = newQuery + " where ";
        }

        // add filter
        // todo before using parameter it should be escaped to eliminate sql injection
        newQuery = newQuery + "o.customer like '%" + filter.customerNameFragment + "%'";

        return newQuery;
    }

    private boolean hasNoWhereClause(String newQuery) {
        return ! newQuery.toLowerCase().contains("where");
    }
}