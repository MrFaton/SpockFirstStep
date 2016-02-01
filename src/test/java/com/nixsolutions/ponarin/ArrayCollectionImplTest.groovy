package com.nixsolutions.ponarin

import com.nixsolutions.ponarin.impl.ArrayCollectionImpl
import spock.lang.Specification

/**
 * Description
 *
 * @author Ponarin Igor
 * @since 01.02.16
 */
class ArrayCollectionImplTest extends Specification {
    ArrayCollection<Integer> arrayCollection
    def baseArray

    def setup() {
        arrayCollection = new ArrayCollectionImpl<>()
        baseArray = [1, 2, null, 3, 4, 2]
        arrayCollection.setArray(baseArray.toArray())
    }

    def "check size at initial state"() {
        given:
        arrayCollection = new ArrayCollectionImpl<>()
        expect:
        arrayCollection.size() == 0
    }

    def "check size after set array"() {
        given:
        arrayCollection.setArray(array.toArray())
        expect:
        arrayCollection.size() == array.size()

        where:
        array << [[], 1..30, [null, null]]
    }

    def "check for empty at initial state"() {
        given:
        arrayCollection = new ArrayCollectionImpl<>()
        expect:
        arrayCollection.isEmpty()
    }

    def "check for empty after set array"() {
        given:
        def isEmpty = { it.size() == 0 }
        arrayCollection.setArray(array.toArray())
        expect:
        arrayCollection.isEmpty() == isEmpty(array)

        where:
        array << [1..10, [], [null, null]]
    }

    def "check contains"() {
        expect:
        arrayCollection.contains(number) == baseArray.contains(number)

        where:
        number << [4, 3, null, 2, 1, 5, 8, -1, -4]
    }

    def "check containsAll with null"() {
        when:
        arrayCollection.containsAll(null)
        then:
        thrown(NullPointerException)

    }

    def "check containsALL"() {
        expect:
        arrayCollection.containsAll(array.toArray()) == baseArray.containsAll(array.toArray())

        where:
        array << [[1, 2, null, 3, 4], [1, 2], [2, null, 3], [null], [4, 5, 6]]
    }

    def "test add"() {
        expect:
        arrayCollection.add(num)
        arrayCollection.getArray().contains(num)

        where:
        num << [1, 1, null, 8, 5, 0, -2, -1, 1]
    }

    def "test addAll"() {
        given:
        def isContainsAll = { firstArr, secondArr ->
            for (elem in secondArr) {
                if (!firstArr.contains(elem)) {
                    return false
                }
            }
            return true
        }
        expect:
        arrayCollection.addAll(arrayToAdd)
        isContainsAll(arrayCollection.getArray(), arrayToAdd)

        where:
        arrayToAdd << [1..3, [null, null], 1..3, [-1, -2, -6, -11]]

    }

    def "check toArray without arguments"() {
        given:
        arrayCollection.setArray(actualArray.toArray())
        expect:
        arrayCollection.toArray() == actualArray

        where:
        actualArray << [1..20, [null, null], [1, null, 2]]
    }

    def "check toArray with arguments"() {
        expect:
        arrayCollection.toArray(type).getAt(0).class == baseArray.toArray(type).getAt(0).class

        where:
        type << [new Integer[0], new Object[0]]
    }

    def "test remove"() {
        expect:
        arrayCollection.remove(num) == removeFlag
        arrayCollection.getArray().contains(num) == containsFlag

        where:
        num | removeFlag | containsFlag
        1   | true       | false
        2   | true       | true
        3   | true       | false
        5   | false      | false
    }

    def "test remove with null"() {
        when:
        arrayCollection.remove(null)
        then:
        thrown(NullPointerException)
    }

}
