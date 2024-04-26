import java.util.Comparator;

/** Tim Sort */
public class Tim<T> implements Sorter<T> {

    static int MIN_MERGE = 32; 

    /** Counter of compare operations */
    long count = 0;
    long timCount = 0;

    /** Establishes ordering of type T */
    private Comparator<T> orderBy;

    /** Constructor for Tim Sort to set comparator
    *
    * @param order Comparator to establish ordering of array elements.
    */
    public Tim(Comparator<T> order) {
        orderBy = order;
    }

    public static int minRunLength(int n) { 
        assert n >= 0; 
  
        // Becomes 1 if any 1 bits are shifted off 
        int r = 0; 
        while (n >= MIN_MERGE) { 
            r |= (n & 1); 
            n >>= 1; 
        } 
        return n + r; 
    }

    public void sort(T[] array) {
        int runLength = minRunLength(MIN_MERGE);

        Insertion<T> insert = new Insertion<>(orderBy);
        
        // Perform inseretion sort on each run
        int r;
        for( int p = 0; p < array.length; p += runLength) {
            if (array.length - 1 < p + runLength - 1) {
                r = array.length - 1;
            }
            else {
                r = p + runLength - 1;
            }
            insert.sort(array, p, r);
        }
        timCount += insert.getCount();

        Merge<T> merge = new Merge<>(orderBy);

        for (int size = runLength; size < array.length; size = 2 * size) { 
            for (int left = 0; left < array.length; left += 2 * size) { 
                int mid = left + size - 1; 

                int right;
                
                if (array.length - 1 < left + (2 * size)-1) {
                    right = array.length - 1;
                }
                else {
                    right = left + (2 * size)-1;
                }
  
                if (mid < right) 
                    merge.merge(array, left, mid, right);
            } 
        } 
        timCount += merge.getCount();
    }

    @Override
    public void setComparator(Comparator<T> order) {
        orderBy = order;
    }

    @Override
    public long getCount() {
        return timCount;
    }
}