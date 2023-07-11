package edu.algorithms.grokaem_algorithms;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgorithmsTest {

    private int[] array = {1, 2, 3, 4, 5, 6, 7, 8};


    @Test
    void canFindTargetViaBinarySearch() {
        // given
        var target = 8;

        // when
        var found = BinarySearch.bs(array, target);

        // then
        assertEquals(array[found], target);
    }

    @Test
    void canFindTargetViaRecursiveBinarySearch() {
        // given
        var target = 2;

        // when
        var found = RecursiveBinarySearch.binarySearchWithRecursion(array, target);

        // then
        assertEquals(array[found], target);
    }

    @Test
    void canFindTargetViaBubbleSort() {
        // given
        var unorderedArray = new int[] {8, 7, 6, 5, 4, 3, 2, 1};

        // when
        BubbleSort.bubbleSort(unorderedArray);

        // then
        Assertions.assertArrayEquals(array, unorderedArray);
    }
}
