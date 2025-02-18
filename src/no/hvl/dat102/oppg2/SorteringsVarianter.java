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
	public static <T extends Comparable<? super T>> void modifiedInsertionSort(T[] a) {
	    // Flytt minste element til posisjon 0
	    int minIndex = 0;
	    for (int i = 1; i < a.length; i++) {
	        if (a[i].compareTo(a[minIndex]) < 0) {
	            minIndex = i;
	        }
	    }
	    T temp = a[minIndex];
	    a[minIndex] = a[0];
	    a[0] = temp;

	    // Sorter resten av tabellen
	    for (int i = 1; i < a.length; i++) {
	        temp = a[i];
	        int j = i - 1;
	        while (a[j].compareTo(temp) > 0) { // Ingen sjekk på j >= 0
	            a[j + 1] = a[j];
	            j--;
	        }
	        a[j + 1] = temp;
	    }
	}
	public static void main(String[] args) {
	    int n = 100000; // Antall elementer (juster for å få minst 5 sekunder)
	    Integer[] a = new Integer[n];

	    // Fyll tabellen med tilfeldige tall
	    for (int i = 0; i < n; i++) {
	        a[i] = (int) (Math.random() * n);
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
