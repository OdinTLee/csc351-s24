public class Merge {
    
    // Sort method to sort array
    public static void sort(Integer[] A){
        sort(A, 0, A.length - 1);
    }

    // Recursive method that splits array and sorts its parts.
    public static void sort(Integer[] A, int p, int r) {
        if(p < r) {
            int q = (p + r) / 2;

            sort(A, p, q);
            sort(A, q + 1, r);

            merge(A, p, q, r);
        }
    } 

    // Method that merges the sorted subarrays
    private static void merge(Integer[] A, int p, int q, int r){

        // Setting the variables n1 and n2 to the size of there respected halves of the array.
        int n1 = (q - p) + 1;
        int n2 = r - q;

        // Using n1 and n2 to create the Left and Right arrays
        Integer[] L = new Integer[n1];
        Integer[] R = new Integer[n2];

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
            if(L[i] <= R[j]) {
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
        }

        // Fill array with the n2 elements
        while(j < n2){
            A[k] = R[j];
            j++;
            k++;
        }
    }
}
