package com.nixsolutions.ponarin;

import java.util.Iterator;

/**
 * Iterator based on array
 *
 * @author Ponarin Igor
 * @since 01.02.16
 */
public interface ArrayIterator<E> extends Iterator<E> {
    boolean hasNext();

    E next();

    Object[] getArray();
}
