package com.nixsolutions.ponarin

import com.nixsolutions.ponarin.impl.ArrayCollectionImpl
import spock.lang.Specification

/**
 * Test of ArrayIteratorImpl
 *
 * @author Ponarin Igor
 * @since 02.02.16
 */
class ArrayIteratorImplTest extends Specification {
    ArrayCollection<Integer> arrayCollection

    def setup() {
        arrayCollection = new ArrayCollectionImpl<>()
    }

    def "test hasNext"() {
        given:
        arrayCollection.setArray(array.toArray())
        expect:
        arrayCollection.iterator().hasNext() == arrayCollection.getArray().iterator().hasNext()

        where:
        array << [[], 1..30, [null, null], []]
    }

    def "test next more than length"() {
        given:
        arrayCollection.setArray(array.toArray())
        def arrIterator = arrayCollection.iterator()
        when:
        (array.size() + 1).times {
            arrIterator.next()
        }
        then:
        thrown(NoSuchElementException)

        where:
        array << [1..5, [null, null], []]
    }

    def "test next"() {
        given:
        arrayCollection.setArray(array.toArray())
        def arrIterator = arrayCollection.iterator()
        def baseArrIterator = array.iterator()
        expect:
        (array.size()).times {
            arrIterator.next() == baseArrIterator.next()
        }


        where:
        array << [1..5, [null, null], [], [1, 5, null, 10]]
    }
}
