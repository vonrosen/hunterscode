package hunterscode.util;

public class QuickSort {

	public static void main(String [] args) {
		
		int [] array = new int[10];
		
		array[0] = 3;
		array[1] = 45;
		array[2] = 5;
		array[3] = 5;
		array[4] = 7;
		array[5] = 2;
		array[6] = 33;
		array[7] = 11;
		array[8] = 12;
		array[9] = 10;
		
		quickSort(array, 0, array.length - 1);
		
		for (int i = 0; i < array.length; ++i)
			System.out.println(array[i]);
	}
	
	public static void quickSort(int [] array, int left, int right) {

		if (left < right) {
			int pivotIndex = choosePivotIndex(left, right);
			
			int pivotValue = array[pivotIndex];
			
			int newPartitionIndex = arrange(array, pivotIndex, pivotValue, left, right);
			
			quickSort(array, left, newPartitionIndex - 1);
			quickSort(array, newPartitionIndex + 1, right);
		}
	}
	
	private static int choosePivotIndex(int left, int right) {
		
		return (left + right) / 2;
	}
	
	private static void swap(int [] array, int swapIndex1, int swapIndex2) {
		
		int swapValue1 = array[swapIndex1];
		
		array[swapIndex1] = array[swapIndex2];
		
		array[swapIndex2] = swapValue1;		
	}
	
	private static int arrange(int [] array, int pivotIndex, int pivotValue, int left, int right) {
		
		swap(array, pivotIndex, right);
		
		int swapIndex = left;
		
		for (int i = left; i < right; ++i) {

			if (array[i] <= pivotValue) {
				
				swap(array, swapIndex, i);
				
				swapIndex++;
			}
		}
		
		swap(array, right, swapIndex);
		
		return swapIndex;
	}
}
