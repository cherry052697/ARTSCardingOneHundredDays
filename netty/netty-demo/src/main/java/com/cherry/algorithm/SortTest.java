package com.cherry.algorithm;

import com.cherry.netty.utils.JsonUtil;

public class SortTest {
	
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
		new SortTest().insertSort(array);
	}

}
