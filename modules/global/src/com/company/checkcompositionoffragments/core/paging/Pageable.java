package com.company.checkcompositionoffragments.core.paging;

import java.io.Serializable;

/**
 * Defines paging of queries.
 * <p>
 * Based on the {@code Pageable} class from Spring Data project.
 */
public interface Pageable {
    /**
     * Returns a {@link Pageable} instance representing no pagination setup.
     *
     * @return
     */
    static Pageable unpaged() {
        return Unpaged.INSTANCE;
    }

    /**
     * Returns whether the current {@link Pageable} contains pagination information.
     *
     * @return
     */
    default boolean isPaged() {
        return true;
    }

    /**
     * Returns whether the current {@link Pageable} does not contain pagination information.
     *
     * @return
     */
    default boolean isUnpaged() {
        return !isPaged();
    }

    /**
     * Returns the page to be returned.
     *
     * @return the page to be returned.
     */
    int getPageNumber();

    /**
     * Returns the number of items to be returned.
     *
     * @return the number of items of that page
     */
    int getPageSize();

    /**
     * Returns the offset to be taken according to the underlying page and page size.
     *
     * @return the offset to be taken
     */
    long getOffset();

    /**
     * Returns the {@link Pageable} requesting the next page.
     *
     * @return
     */
    Pageable next();

    /**
     * Returns the previous {@link Pageable} or the first {@link Pageable} if the current one already is the first one.
     *
     * @return
     */
    Pageable previousOrFirst();

    /**
     * Returns the {@link Pageable} requesting the first page.
     *
     * @return
     */
    Pageable first();

    /**
     * Returns whether there's a previous {@link Pageable} we can access from the current one. Will return
     * {@literal false} in case the current {@link Pageable} already refers to the first page.
     *
     * @return
     */
    boolean hasPrevious();
}
