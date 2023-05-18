package FirstStap.grokaem_algorithms;

import java.util.Arrays;
import java.util.Random;

public class TestQuicksort {
    public static void main(String[] args) {
        Random random = new Random();
        int[] numbers = new int[10];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100);
        }

        System.out.println("Before");
        Arrays.stream(numbers).forEach(System.out::println);
        Quicksort.quicksort(numbers);
        System.out.println("\nAfter");
        Arrays.stream(numbers).forEach(System.out::println);
    }
}

class Quicksort {

    public static void quicksort(int[] arr){
        quicksort(arr, 0, arr.length - 1);
    }
    // method to find the partition position
    private static void quicksort(int[] arr, int lowIndex, int highIndex) {
        // Declare base case
        if (lowIndex >= highIndex) {
            return;
        }

        // 1. Choose the pivot randomly for better performance
        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, highIndex);

        // 2. Search the index
        int lp = partition(arr, lowIndex, highIndex, pivot);

        // 3. Recursively quicksort left and right sub parts
        quicksort(arr, lowIndex, lp - 1);
        quicksort(arr, lp + 1, highIndex);
    }

    // Move all the numbers lower than the pivot to the left
    // and all the numbers greater than the pivot to the right
    private static int partition(int[] arr, int lowIndex, int highIndex, int pivot) {
        // 2.1 declare left pointer and right pointer
        int lp = lowIndex;
        int rp = highIndex;

        while (lp < rp) {
            while (arr[lp] <= pivot && lp < rp) {
                lp++;
            }

            while (arr[rp] >= pivot && lp < rp) {
                rp--;
            }

            swap(arr, lp, rp);
        }
        swap(arr, lp, highIndex);
        return lp;
    }

    // 2.2 Swap the numbers
    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
