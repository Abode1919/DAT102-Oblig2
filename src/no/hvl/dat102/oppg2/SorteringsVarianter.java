package no.hvl.dat102.oppg2;

public class SorteringsVarianter {
	public static <T extends Comparable<? super T>> void insertionSort(T[] a) {
	    for (int i = 1; i < a.length; i++) {
	        T temp = a[i];
	        int j = i - 1;
	        while (j >= 0 && a[j].compareTo(temp) > 0) {
	            a[j + 1] = a[j];
	            j--;
	        }
	        a[j + 1] = temp;
	    }
	}
	private static <T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static <T extends Comparable<T>> void modifiedInsertionSort(T[] a) {
        int n = a.length;

        int minIndex = 0;
        for (int i = 1; i < n; i++) {
            if (a[i].compareTo(a[minIndex]) < 0) {
                minIndex = i;
            }
        }
        swap(a, 0, minIndex);

        for (int i = 2; i < n; i += 2) {
            T min = a[i];
            T max = a[i + 1];

            if (min.compareTo(max) > 0) {
                swap(a, i, i + 1);
                min = a[i];
                max = a[i + 1];
            }

            int j = i - 1;

            while (j >= 0 && a[j].compareTo(max) > 0) {
                a[j + 2] = a[j];
                j--;
            }

            a[j + 2] = max;

            while (j >= 0 && a[j].compareTo(min) > 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = min;
        }

        if (n % 2 != 0) {
            T last = a[n - 1];
            int j = n - 2;

            while (j >= 0 && a[j].compareTo(last) > 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = last;
        }
    }

	public static void main(String[] args) {
	    int n = 100000; // Antall elementer (juster for å få minst 5 sekunder)
	    Integer[] a = new Integer[n];

	    // Fyll tabellen med tilfeldige tall
	    for (int i = 0; i < n; i++) {
	        a[i] = (int) (Math.random() * 1000);
	    }

	    // Mål tid for original insertion sort
	    long startTime = System.nanoTime();
	    insertionSort(a.clone());
	    long endTime = System.nanoTime();
	    System.out.println("Original Insertion Sort: " + (endTime - startTime) / 1_000_000_000.0 + " sekunder");

	    // Mål tid for modifisert insertion sort
	    startTime = System.nanoTime();
	    modifiedInsertionSort(a.clone());
	    endTime = System.nanoTime();
	    System.out.println("Modified Insertion Sort: " + (endTime - startTime) / 1_000_000_000.0 + " sekunder");

	}

}
