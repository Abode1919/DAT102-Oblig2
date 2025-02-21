package no.hvl.dat102.oppg2;

public class QuickSort {
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

    public static void main(String[] args) {
        int n = 32000; // Størrelse på arrayet
        Integer[] array = new Integer[n];

        // Fyll arrayet med like verdier
        for (int i = 0; i < n; i++) {
            array[i] = 42; // Alle elementene er like
        }

        // Mål tid for QuickSort
        long startTime = System.nanoTime();
        Sortering.quickSort(array, 0, array.length - 1);
        long endTime = System.nanoTime();

        // Beregn tid i sekunder
        double timeInSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("QuickSort T(n) for like elementer (" + n + " tall): " + timeInSeconds + " sekunder");
    }
}
