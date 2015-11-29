package hunterscode.util;

public class BinarySearch {

    //all arguments except last are for array to search, last argument is search value
    public static void main(String [] args) {        
        if (args != null && args.length > 0) {
            
            int [] array = new int[args.length - 1];

            for (int i = 0; i < args.length - 1; ++i) {
                array[i] = Integer.parseInt(args[i]);
            }
            
            int searchValue = Integer.parseInt(args[args.length - 1]);

            QuickSort.quickSort(array, 0, array.length - 1);

            int index = binarySearch(array, searchValue, 0, array.length - 1);
            
            if (index == -1) {
                System.out.println("The value was not found!");
                return;
            }
            
            System.out.println("The value " + searchValue + " returned index "
                    + index + " which is " + array[index]);
        }
    }

    public static int binarySearch(int [] array, int searchValue, int start, int end) {
        int midIndex = (start + end)/2;
        
        if (start > midIndex) return -1;
        
        if (array[midIndex] > searchValue) {
            return binarySearch(array, searchValue, start, midIndex - 1);
        }
        else if (array[midIndex] < searchValue) {
            return binarySearch(array, searchValue, midIndex + 1, end);            
        }
        else {
            return midIndex;
        }
    }    
}