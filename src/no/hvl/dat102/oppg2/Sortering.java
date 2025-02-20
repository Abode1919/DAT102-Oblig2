package no.hvl.dat102.oppg2;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class Sortering {

        // Insertion Sort
        public static <T extends Comparable<T>> void insertionSort(T[] a) {
            for (int i = 1; i < a.length; i++) {
                T key = a[i];
                int j = i - 1;

                while (j >= 0 && a[j].compareTo(key) > 0) {
                    a[j + 1] = a[j];
                    j--;
                }
                a[j + 1] = key;
            }
        }

        // Selection Sort
        public static <T extends Comparable<T>> void selectionSort(T[] a) {
            for (int i = 0; i < a.length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < a.length; j++) {
                    if (a[j].compareTo(a[minIndex]) < 0) {
                        minIndex = j;
                    }
                }
                // Bytt minste elementet med første element
                T temp = a[minIndex];
                a[minIndex] = a[i];
                a[i] = temp;
            }
        }

        // Quick Sort
        public static <T extends Comparable<T>> void quickSort(T[] a, int low, int high) {
            if (low < high) {
                int pi = partition(a, low, high);
                quickSort(a, low, pi - 1);
                quickSort(a, pi + 1, high);
            }
        }

        private static <T extends Comparable<T>> int partition(T[] a, int low, int high) {
            T pivot = a[high];
            int i = (low - 1);
            for (int j = low; j < high; j++) {
                if (a[j].compareTo(pivot) < 0) {
                    i++;
                    T temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
            T temp = a[i + 1];
            a[i + 1] = a[high];
            a[high] = temp;
            return i + 1;
        }

        // Merge Sort
        public static <T extends Comparable<T>> void mergeSort(T[] a) {
            if (a.length < 2) {
                return;
            }
            int mid = a.length / 2;
            T[] left = (T[]) new Comparable[mid];
            T[] right = (T[]) new Comparable[a.length - mid];

            System.arraycopy(a, 0, left, 0, mid);
            System.arraycopy(a, mid, right, 0, a.length - mid);

            mergeSort(left);
            mergeSort(right);
            merge(a, left, right);
        }

        private static <T extends Comparable<T>> void merge(T[] a, T[] left, T[] right) {
            int i = 0, j = 0, k = 0;
            while (i < left.length && j < right.length) {
                if (left[i].compareTo(right[j]) <= 0) {
                    a[k++] = left[i++];
                } else {
                    a[k++] = right[j++];
                }
            }
            while (i < left.length) {
                a[k++] = left[i++];
            }
            while (j < right.length) {
                a[k++] = right[j++];
            }
        }
        public static long measureSortTime(Runnable sortMethod, Integer[] a) {
            Instant start = Instant.now();
            sortMethod.run();
            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);
            return duration.toMillis();  // Returns time in milliseconds
        }

        // Hovedmetode for testing
        public static <T extends Comparable<T>> void testSortAlgorithm(String name, T[] array, Runnable sortMethod) {
            long startTime = System.nanoTime();
            sortMethod.run();
            long endTime = System.nanoTime();
            double seconds = (endTime - startTime) / 1_000_000_000.0;
            System.out.println(name + " T(n): " + seconds + " sekunder");
        }

    // Hovedmetode for testing
    public static void main(String[] args) {
        int[] testSizes = {32000, 64000, 128000};

        for (int n : testSizes) {
            System.out.println("\nTester sorteringsalgoritmer for n = " + n);
            Integer[] a = new Integer[n];

            // Fyll arrayet med tilfeldige tall
            for (int i = 0; i < n; i++) {
                a[i] = (int) (Math.random() * 1000);
            }

            // Test hver sorteringsalgoritme
            testSortAlgorithm("Insertion Sort", Arrays.copyOf(a, a.length), () -> insertionSort(Arrays.copyOf(a, a.length)));
            testSortAlgorithm("Selection Sort", Arrays.copyOf(a, a.length), () -> selectionSort(Arrays.copyOf(a, a.length)));
            testSortAlgorithm("Quick Sort", Arrays.copyOf(a, a.length), () -> quickSort(Arrays.copyOf(a, a.length), 0, a.length - 1));
            testSortAlgorithm("Merge Sort", Arrays.copyOf(a, a.length), () -> mergeSort(Arrays.copyOf(a, a.length)));
        }
    }
}