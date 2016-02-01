package com.nixsolutions.ponarin;

import java.util.Iterator;

/**
 * Description
 *
 * @author Ponarin Igor
 * @since 01.02.16
 */
public interface ArrayIterator<E> extends Iterator<E> {
    boolean hasNext();

    E next();

    Object[] getArray();
}
