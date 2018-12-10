package com.example.demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestComparator {
	public static void main(String[] args) {
		TestComparator tc = new TestComparator();
//		tc.testListString();
		tc.testListInteger();
	}
	public void testListInteger(){
		List<Integer> numbers = Arrays.asList(6, 2, 1, 4, 9);
		System.out.println(numbers); //[6, 2, 1, 4, 9]
		numbers.sort(Comparator.naturalOrder());
		System.out.println(numbers); //[1, 2, 4, 6, 9]
	}
	
	public void testListString(){
		List<String> cities = Arrays.asList("Milan", "london","San Francisco","Tokyo","New Delhi");
			System.out.println(cities);
			//[Milan, london, San Francisco, Tokyo, New Delhi]
			cities.sort(String.CASE_INSENSITIVE_ORDER);
			System.out.println(cities);
			//[london, Milan, New Delhi, San Francisco, Tokyo]
			cities.sort(Comparator.naturalOrder());
			System.out.println(cities);
	}

}
