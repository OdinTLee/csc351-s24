public class Counting {

    // Method to sort array.
    public static void sort(Integer[] A){
        
        Integer k = A[0];

        for(int i = 0; i < A.length; i++){
            if(A[i] >= k){
                k = A[i];
            }
        }

        // k needs to be the index thus C array of size 11
        Integer[] C = new Integer[k + 1];

        // Filling array with 0s
        for(int i = 0; i < C.length; i++){
            C[i] = 0;
        }

        // Creating histogram
        for(int j = 0; j < A.length; j++){
            C[A[j]] = C[A[j]] + 1;
        }

        // C[i] has # of elements less than or equal to i
        for(int i = 1; i < C.length; i++){
            C[i] = C[i] + C[i - 1];
        }

        // Initialize tmp array B to hold sorted array.
        Integer[] B = new Integer[A.length];

        // Fill sorted array B from counts in C.
        for(int j = A.length - 1; j >=0; j--) {
            B[C[A[j]] -1] = A[j];
            C[A[j]] = C[A[j]] -1;
        }

        // Copy sorted elements back into array A
        for(int i = 0; i < A.length; i++) {
            A[i] = B[i];
        }
    }
}

