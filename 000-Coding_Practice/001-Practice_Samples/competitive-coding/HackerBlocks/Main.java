package HackerBlocks;

public class Main {
    public static void main(String args[]) {
        int[] arr = { 4,1,2,6,3,8,9,10,11,5};
        System.out.println("Before Sorting:");
        printArr(arr);
        inPlaceHeapSort(arr);
        System.out.println();
        System.out.println("After Sorting:");
        printArr(arr);
        System.out.println();
    }
    
    public static void printArr(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    public static void inPlaceHeapSort(int[] arr) {
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			downheapify(arr, i, arr.length - 1);
		}
		for (int i = 0; i < arr.length - 1; i++) {
			swap(arr, 0, arr.length - i - 1);
			downheapify(arr, 0, arr.length - i - 1);
		}
	}

	public static void downheapify(int[] arr, int pi, int lp) {
	    System.out.println("downHeapify:"+pi);
		int lci = 2 * pi + 1;
		int rci = 2 * pi + 2;
		int maxi = pi;
		if (lci < lp && arr[lci] > arr[maxi]) {
			maxi = lci;
		}
		if (rci < lp && arr[rci] > arr[maxi]) {
			maxi = rci;
		}
		if (maxi != pi) {
			swap(arr, maxi, pi);
			System.out.println();
			printArr(arr);
			downheapify(arr, maxi, lp);
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
