package com.nixsolutions.ponarin;

import java.util.Collection;

/**
 * Collection based on array (like ArrayList)
 *
 * @author Ponarin Igor
 * @since 01.02.16
 */
public interface ArrayCollection<E> extends Collection<E> {
    Object[] getArray();

    void setArray(E[] array);
}
