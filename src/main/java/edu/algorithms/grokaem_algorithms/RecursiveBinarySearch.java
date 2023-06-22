package edu.algorithms.grokaem_algorithms;

public class RecursiveBinarySearch {

    public static int binarySearchWithRecursion(int[] sortedArray, int key) {
        return binarySearchRecursively(sortedArray, key, 0, sortedArray.length);
    }

    private static int binarySearchRecursively(int[] sortedArray, int guess, int low, int high) {
        int m = (low + high) / 2;

        if (high < low) {
            return -1;
        }

        if (guess == sortedArray[m]) {
            return m;
        } else if (guess < sortedArray[m]) {
            return binarySearchRecursively(sortedArray, guess, low, m - 1);
        } else {
            return binarySearchRecursively(sortedArray, guess, m + 1, high);
        }
    }
}
