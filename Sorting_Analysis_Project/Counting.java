import java.util.function.Function;
import java.util.*;

/** Counting Sort assumes key of type Integer */
public class Counting<T> implements Sorter<T> {

    /** Extracts the key from an object in the array */
    Function<T,Integer> keyGetter = null;

    /** Max Value in the array to be sorted */
    Integer maxValue = null;

    /** Counter of loop iterations */
    long count = 0;

    /** Default empty constructor. */
    public Counting() {}

        /** Constructor for Counting
        *
        * @param order Comparator to establish ordering of array elements.
        */
        public Counting(Function<T,Integer> getter) {
            keyGetter = getter;
        }

        /** Constructor for Counting with known max value
        *
        * @param order Comparator to establish ordering of array elements.
        */
        public Counting(Function<T,Integer> getter, Integer maximum) {
            keyGetter = getter;
            this.maxValue = maximum;
        }

        /** Sorts specified array using Counting Sort. Inplace version of the sorter.
        *
        * @param array Array to be sorted.
        */
        @Override
        public void sort(T[] B) {

            /*
            Counting sort is not an in-place sorting algorithm.
            To work around this, first the contents of array are copied
            into another array called "unsorted", which is the "A" array.
            When it is time to place the contents into the "B" array,
            copy them from the unsorted "A" array into "array".
            */
            @SuppressWarnings("unchecked")
            T[] A = (T[]) new Object[B.length];
            for (int i=0; i<B.length; i++) {
                A[i] = B[i];
            }

            int k = keyGetter.apply(A[0]);
            for(int i = 0; i < A.length; i++){
                if(keyGetter.apply(A[i]) >= k) {
                    k = keyGetter.apply(A[i]);
                }
                count++;
            }

            // k needs to be the index thus C array of size 11
            int[] C = new int[k + 1];

            // Filling array with 0s
            for(int i = 0; i < C.length; i++){
                C[i] = 0;
                count++;
            }

            // Creating histogram
            for(int j = 0; j < A.length; j++){
                C[keyGetter.apply(A[j])] = C[keyGetter.apply(A[j])] + 1;
                count++;
            }

            // C[i] has # of elements less than or equal to i
            for(int i = 1; i < C.length; i++){
                C[i] = C[i] + C[i - 1];
                count++;
            }

            // Fill sorted array B from counts in C.
            for(int j = A.length - 1; j >=0; j--) {
                B[C[keyGetter.apply(A[j])] -1] = A[j];
                C[keyGetter.apply(A[j])] = C[keyGetter.apply(A[j])] - 1;
                count++;
            }

            // Copy sorted elements back into array A
            for(int i = 0; i < A.length; i++) {
                A[i] = B[i];
                count++;
            }
        } // end sort(T[])


        private Integer findMax(T[] B) {
            Integer max = keyGetter.apply(B[0]);
            for (T element : B) {
                Integer valueOf = keyGetter.apply(element);
                if (valueOf > max) {
                    max = valueOf;
                }
            }
            return max;
        } // end findMax()

        public void setKeyGetter(Function<T,Integer> getter) {
            keyGetter = getter;
        }

        @Override
        public long getCount() {
            return count;
        }

        @Override
        public void setComparator(Comparator<T> c) {
            // not relevant for counting sort
        }
    } // end class Counting
