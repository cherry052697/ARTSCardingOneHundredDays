package com.cherry.algorithm;

import com.cherry.netty.utils.JsonUtil;

public class SortTest2 {
		
	 void quickSort(int arr[],int _left,int _right){
	        int left = _left;
	        int right = _right;
	        int temp = 0;
	        if(left <= right){   //待排序的元素至少有两个的情况
	            temp = arr[left];  //待排序的第一个元素作为基准元素
	            while(left != right){   //从左右两边交替扫描，直到left = right

	                while(right > left && arr[right] >= temp)  
	                     right --;        //从右往左扫描，找到第一个比基准元素小的元素
	                  arr[left] = arr[right];  //找到这种元素arr[right]后与arr[left]交换

	                while(left < right && arr[left] <= temp)
	                     left ++;         //从左往右扫描，找到第一个比基准元素大的元素
	                  arr[right] = arr[left];  //找到这种元素arr[left]后，与arr[right]交换

	            }
	            arr[right] = temp;    //基准元素归位
	            System.out.println(JsonUtil.toJson(arr));
	            quickSort(arr,_left,left-1);  //对基准元素左边的元素进行递归排序
	            quickSort(arr, right+1,_right);  //对基准元素右边的进行递归排序
	        }        
	    }

	//归并
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
		System.out.println(JsonUtil.toJson(tempArray));
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
//		System.out.println(JsonUtil.toJson(array2));
//		new SortTest2().mergeSort(array2);
//		System.out.println(JsonUtil.toJson(array2));
		int [] array3 = {8,1,4,9,6,3,5,2,7,0};
		new SortTest2().quickSort(array3, 0, array3.length-1);
	}

}
