package com.cherry.algorithm;

import com.cherry.netty.utils.JsonUtil;

public class SortTest2 {
	
	void mergeSort(int [] a){
		int [] tempArray = new int[a.length];
		mergeSort(a,tempArray,0,a.length-1);
	}
	
	
	private void mergeSort(int[] a, int[] tempArray, int left, int right) {
		if(left < right){
			int center = (left+right)/2;
			mergeSort(a,tempArray,left,center);
			mergeSort(a, tempArray, center+1, right);
			mergeSort(a, tempArray, left,center+1, right);
		}
	}


	private void mergeSort(int[] a, int[] tempArray, int leftPos, int rightPos, int rightEnd) {
		int leftEnd = rightPos-1;
		int tempPos = leftPos;
		int numElements = rightEnd-leftPos+1;
		while(leftPos <= leftEnd && rightPos <= rightEnd)
			if (a[leftPos] <= a[rightPos])
				tempArray[tempPos++] = a[leftPos++];
			else
				tempArray[tempPos++] = a[rightPos++];
		
		while(leftPos <= leftEnd)
			tempArray[tempPos++] = a[leftPos++];
		
		while(rightPos <= rightEnd)
			tempArray[tempPos++] = a[rightPos++];
		
		for(int i = 0; i<numElements;i++,rightEnd--)
			a[rightEnd] = tempArray[rightEnd];
	}


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
//		new SortTest2().insertSort(array);
		int [] array2 = {24,13,26,1,2,27,38,15};
		System.out.println(JsonUtil.toJson(array2));
		new SortTest2().mergeSort(array2);
		System.out.println(JsonUtil.toJson(array2));
	}

}
