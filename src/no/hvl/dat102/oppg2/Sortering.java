package no.hvl.dat102.oppg2;

import java.util.Random;

public class Sortering {

    // Insertion Sort
    public static void insertionSort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Selection Sort
    public static void selectionSort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // Quick Sort
    public static void quickSort(Integer[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Integer[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Merge Sort
    public static void mergeSort(Integer[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(Integer[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Integer[] L = new Integer[n1];
        Integer[] R = new Integer[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }



    public class Main {
        public static void main(String[] args) {
            int[] sizes = {32000, 64000, 128000};
            for (int n : sizes) {
                Integer[] arr = generateRandomArray(n);

                long startTime = System.nanoTime();
                Sortering.insertionSort(arr.clone());
                long endTime = System.nanoTime();
                long insertionTime = endTime - startTime;

                startTime = System.nanoTime();
                Sortering.selectionSort(arr.clone());
                endTime = System.nanoTime();
                long selectionTime = endTime - startTime;

                startTime = System.nanoTime();
                Sortering.quickSort(arr.clone(), 0, arr.length - 1);
                endTime = System.nanoTime();
                long quickTime = endTime - startTime;

                startTime = System.nanoTime();
                Sortering.mergeSort(arr.clone(), 0, arr.length - 1);
                endTime = System.nanoTime();
                long mergeTime = endTime - startTime;

                System.out.println("N = " + n);
                System.out.println("Insertion Sort: " + insertionTime + " ns");
                System.out.println("Selection Sort: " + selectionTime + " ns");
                System.out.println("Quick Sort: " + quickTime + " ns");
                System.out.println("Merge Sort: " + mergeTime + " ns");
                System.out.println();
            }
        }

        private static Integer[] generateRandomArray(int n) {
            Random rand = new Random();
            Integer[] arr = new Integer[n];
            for (int i = 0; i < n; i++) {
                arr[i] = rand.nextInt();
            }
            return arr;
        }
    }
}


