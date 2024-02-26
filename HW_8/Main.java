import java.util.*;

public class Main {
    public static void main(String[] args) {

        Integer[] A = { 14, 5, 8, 7, 1, 0, 8, 3, 6, 9, 1 };
		Counting.sort(A);
        // print the values to see if sorted
        for( Integer n : A){
            System.out.print(n + " ");
        }

        System.out.println();

        Integer[] B = { 4, 6, 7, 2, 9, 1, 13, 17, 4, 9, 10 };
		Merge.sort(B);
        // print the values to see if sorted
        for( Integer n : B){
            System.out.print(n + " ");
        }
    }
}