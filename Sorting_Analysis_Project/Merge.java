import java.util.Comparator;

/** Merge Sort divide-and-conquer recursive algorithm */
public class Merge<T> implements Sorter<T> {

    /** Establishes ordering of type T */
    private Comparator<T> orderBy;

    /** Counter of compare operations */
    long count = 0;


    /** Constructor for Merge Sort to set comparator
    *
    * @param order Comparator to establish ordering of array elements.
    */
    public Merge(Comparator<T> order) {
        orderBy = order;
    }

    /** Sorts specified array using Merge Sort
    *
    * @param array Array to be sorted.
    */
    @Override
    public void sort(T[] array) {

        // Reset count back to 0
        count = 0;

        mergeSort(array, 0, array.length - 1);
        
    }

    // Recursive method that splits array and sorts its parts.
    public void mergeSort(T[] A, int p, int r) {
        if(p < r) {
            int q = (p + r) / 2;

            mergeSort(A, p, q);
            mergeSort(A, q + 1, r);

            merge(A, p, q, r);
        }
    } 
    
    // Method that merges the sorted subarrays
    public void merge(T[] A, int p, int q, int r){
    
        // Setting the variables n1 and n2 to the size of there respected halves of the array.
        int n1 = (q - p) + 1;
        int n2 = r - q;

        // Using n1 and n2 to create the Left and Right arrays
        @SuppressWarnings("unchecked")
        T[] L = (T[]) new Object[n1];
        @SuppressWarnings("unchecked")
        T[] R = (T[]) new Object[n2];

        // Using a for loop fill in the L[] with its respected elements
        for (int i = 0; i < n1; i++){
            L[i] = A[p + i];
        }
        
        // Using a for loop fill in the R[] with its respected elements
        for (int j = 0; j < n2; j++){
            R[j] = A[(q + 1) + j];
        }    

        int i = 0;
        int j = 0;
        int k = p;

        // Compare the elements of the L and R arrays and set the smaller alement into the A array
        while(i < n1 && j < n2) {
            count++;
            if (orderBy.compare(L[i], R[j]) <= 0){
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
            k++;
        }

        // Fill array with the n1 elements
        while(i < n1){
            A[k] = L[i];
            i++;
            k++;
            count++;
        }

        // Fill array with the n2 elements
        while(j < n2){
            A[k] = R[j];
            j++;
            k++;
            count++;
        }
    } // end sort(T[])

    @Override
    public void setComparator(Comparator<T> order) {
        orderBy = order;
    }

    @Override
    public long getCount() {
        return count;
    }
} // end class Merge
