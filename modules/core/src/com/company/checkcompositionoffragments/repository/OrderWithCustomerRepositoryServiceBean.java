package com.company.checkcompositionoffragments.repository;

import com.company.checkcompositionoffragments.core.filter.Filter;
import com.company.checkcompositionoffragments.core.filter.FilterSpecification;
import com.company.checkcompositionoffragments.core.paging.Pageable;
import com.company.checkcompositionoffragments.dto.OrderWithCustomerDbView;
import com.company.checkcompositionoffragments.exception.UnsupportedFilterException;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.global.Sort;
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


        // apply sort
        String queryWithSort = queryWithFilters;
        for (Sort.Order order : sort.getOrders()) {
            queryWithSort = applySort(queryWithSort, order);
        }

        // apply pagination
        String queryWithPagination = queryWithSort;

        return txDm.load(OrderWithCustomerDbView.class).query(queryWithPagination).list();
    }

    protected String applySort(String queryWithSort, Sort.Order order) {
        return null;
    }

    private String constructWhereClause(String query, FilterSpecification<OrderWithCustomerDbView> orderWithCustomerDbViewFilter) {
        return null;
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
    protected List<Class> findAllOrderWithCustomersSupportedFilters() {
        return Arrays.asList(ByCustomerNameContains.class);
    }

    private String applyByCustomerNameContainsFilter(String query, ByCustomerNameContains filter) {
        return query;
    }
}