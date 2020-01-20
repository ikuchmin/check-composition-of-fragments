package com.company.checkcompositionoffragments.core.paging;

import javax.annotation.Nullable;

/**
 * Basic Java Bean implementation of {@code Pageable}.
 *
 * Based on the {@code PageRequest} class from Spring Data project.
 */
public class PageRequest extends AbstractPageRequest {

    private static final long serialVersionUID = -4541509938956089562L;

    /**
     * Creates a new {@link PageRequest}.
     *
     * @param page zero-based page index, must not be negative.
     * @param size the size of the page to be returned, must be greater than 0.
     */
    protected PageRequest(int page, int size) {

        super(page, size);
    }

    /**
     * Creates a new {@link PageRequest} with sort parameters applied.
     *
     * @param page zero-based page index.
     * @param size the size of the page to be returned.
     * @since 2.0
     */
    public static PageRequest of(int page, int size) {
        return new PageRequest(page, size);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#next()
     */
    @Override
    public Pageable next() {
        return new PageRequest(getPageNumber() + 1, getPageSize());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.AbstractPageRequest#previous()
     */
    @Override
    public PageRequest previous() {
        return getPageNumber() == 0 ? this : new PageRequest(getPageNumber() - 1, getPageSize());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#first()
     */
    @Override
    public Pageable first() {
        return new PageRequest(0, getPageSize());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(@Nullable Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PageRequest)) {
            return false;
        }

        PageRequest that = (PageRequest) obj;

        return super.equals(that);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return 31 * super.hashCode();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("Page request [number: %d, size %d]", getPageNumber(), getPageSize());
    }
}