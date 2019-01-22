package com.cherry.algorithm;

import com.cherry.netty.utils.JsonUtil;

public class SortTest {
	
	 /**
	  * 快速排序
	 * @param arr
	 * @param _left
	 * @param _right
	 */
	void quickSort(int arr[],int _left,int _right){
	        int left = _left;
	        int right = _right;
	        int temp = 0;
	        if(left <= right){   		//待排序的元素至少有两个的情况
	            temp = arr[left];  		//待排序的第一个元素作为基准元素
	            while(left != right){   //从左右两边交替扫描，直到left = right

	                while(right > left && arr[right] >= temp)  
	                     right --;        		//从右往左扫描，找到第一个比基准元素小的元素
	                  arr[left] = arr[right];  	//找到这种元素arr[right]后与arr[left]交换

	                while(left < right && arr[left] <= temp)
	                     left ++;         		//从左往右扫描，找到第一个比基准元素大的元素
	                  arr[right] = arr[left];  	//找到这种元素arr[left]后，与arr[right]交换

	            }
	            arr[right] = temp;    				//基准元素归位
	            System.out.println(JsonUtil.toJson(arr));
	            quickSort(arr,_left,left-1);  		//对基准元素左边的元素进行递归排序
	            quickSort(arr, right+1,_right);  	//对基准元素右边的进行递归排序
	        }        
	    }
	
	/**
	 * 递归排序
	 * @param a
	 * @param tempArray
	 * @param left
	 * @param right
	 */
	void mergeSort(int[] a){
		int[] temp = new int[a.length];
		mergeSort(a, temp, 0,a.length-1);
	}

	void mergeSort(int[] a,int[] tempArray,int left,int right){
		if (left<right) {
			int center = (left+right)/2;
			mergeSort(a,tempArray, left, center);
			mergeSort(a, tempArray, center+1, right);
			merge(a,tempArray,left,center+1,right);
		}
	}
	
	private void merge(int[] a, int[] tempArray, int leftPos, int rightPos, int rightEnd) {
		int leftEnd = rightPos - 1;
		int tempPos = leftPos;
		int numElements = rightEnd-leftPos+1;
		while(leftPos <= leftEnd && rightPos <= rightEnd)
			if(a[leftPos] <= a[rightPos])
				tempArray[tempPos++] = a[leftPos++];
			else
				tempArray[tempPos++] = a[rightPos++];
		
		while(leftPos <= leftEnd)
			tempArray[tempPos++] = a[leftPos++];
		
		while(rightPos <= rightEnd)
			tempArray[tempPos++] = a[rightPos++];
		
		for (int i = 0; i < numElements; i++,rightEnd--) 
			a[rightEnd] = tempArray[rightEnd];
		
	}
	

	/**
	 * 堆排序
	 * @param a
	 */
	void heapSort(int[] a){
		for (int i = a.length/2; i >= 0 ; i--) 
			percDown(a,i,a.length);
		for (int i = a.length-1; i > 0 ; i--) {
			swapReferences(a,0,i);
			percDown(a, 0, i);
		}
	}
	
	
	private void swapReferences(int[] a, int i, int i2) {
		
	}


	private void percDown(int[] a, int i, int length) {
		int child = 0;
		int tmp;
		for (tmp = a[i]; leftChild(i) < length; i = child) {
			child = leftChild(i);
			if (child != length-1 && a[child]<a[child+1])
				child++;
			if(tmp<a[child])
				a[i] = a[child];
			else
				break;
		}
		a[i] = tmp;
	}


	private int leftChild(int i) {
		return 2*i+1;
	}


	/**
	 * 希尔排序
	 * @param a
	 */
	void shellSort(int[] a){
		int j;
		for (int gap = a.length/2; gap > 0; gap /= 2) 
			for (int i = gap; i < a.length; i++) {
				int tmp = a[i];
				for (j = i;  j >= gap&&tmp<a[j-gap]; j -= gap) 
					a[j] = a[j-gap];
				a[j] = tmp;
				System.out.println("gap="+gap+";i="+i+";j="+j+"\t"+JsonUtil.toJson(a));
			}
	}
	
	/**
	 * 插入排序
	 * @param a
	 */
	void insertSort(int[] a){
		int j;
		for (int i = 0; i < a.length; i++) {
			int temp = a[i];
			for (j = i; j > 0 && temp < a[j-1]; j--) 
				a[j] = a[j-1];
			a[j] = temp;
			System.out.println(JsonUtil.toJson(a));
		}
	}
	public static void main(String[] args) {
		int [] array = {21,32,51,64,8,34};
//		new SortTest().insertSort(array);
		int [] array2 = {81,94,11,96,12,35,17,95,28,58,41,75,15};
		new SortTest().shellSort(array2);
	}

}
