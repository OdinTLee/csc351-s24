import java.util.Comparator;

/** Insertion sort - an in-place sorting algorithm */
public class Insertion<T> implements Sorter<T> {

    /** Establishes ordering of type T */
    private Comparator<T> orderBy;

    /** Counter of compare operations */
    long count = 0;


    /** Constructor for Insertion Sort to set comparator
    *
    * @param order Comparator to establish ordering of array elements.
    */
    public Insertion(Comparator<T> order) {
        orderBy = order;
    }

    /** Sorts specified array using Insertion Sort. Inplace sorter.
    *
    * @param array Array to be sorted.
    */
    public void sort(T[] array) {

        // Reset count back to 0
        count = 0;

        // _________________________________________________________________
        // TODO ___________ COMPLETE INSERTION SORT BELOW __________________
        // ________________   IMPORTANT TO COMMENT YOUR CODE _______________
        
        for (int j = 1; j < array.length; j++){
            // Store key into sorted array
            T key = array[j];
            int i = j - 1;

            // Shift keys forward and still look for index to insert key
            while (i >= 0 && orderBy.compare(array[i], key) > 0){
                array[i + 1] = array[i];
                i--;
                count++;
            }
            // Place key into spot
            array[i + 1] = key;
        }
    } // end sort(T[])

    /** Sorts specified array using Insertion Sort. Inplace sorter.
    *
    * @param array Array to be sorted.
    */
    public void sort(T[] array, int p, int r) { 
        for (int i = p + 1; i <= r; i++) { 
            T key = array[i]; 
            int j = i - 1; 
            while (j >= 0 && orderBy.compare(array[j], key) > 0){
                array[j + 1] = array[j]; 
                j--;
                count++;
            } 
            array[j + 1] = key; 
        } 
    } 

    @Override
    public void setComparator(Comparator<T> order) {
        orderBy = order;
    }

    @Override
    public long getCount() {
        return count;
    }
} // end class Insertion
