package no.hvl.dat102.oppg2;

import java.util.Arrays;
import java.util.Random;
import java.util.Arrays;
import java.util.Random;

public class QuickSortAllEqual {

    public static void quickSort(Integer[] arr, int low, int high) {
        if (low >= high) return;

        int lt = low, gt = high;
        int pivot = arr[low];
        int i = low;

        while (i <= gt) {
            if (arr[i] < pivot) swap(arr, lt++, i++);
            else if (arr[i] > pivot) swap(arr, i, gt--);
            else i++;
        }

        quickSort(arr, low, lt - 1);
        quickSort(arr, gt + 1, high);
    }

    private static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 32000;
        Integer[] identicalArray = new Integer[n];
        Arrays.fill(identicalArray, 42); // Fyller arrayet med samme tall

        long startTime = System.nanoTime();
        quickSort(identicalArray, 0, n - 1);
        long endTime = System.nanoTime();

        double elapsedTime = (endTime - startTime) / 1e6; // Konverterer til millisekunder
        System.out.println("Tid brukt for Quicksort med identiske elementer: " + elapsedTime + " ms");
    }
}
