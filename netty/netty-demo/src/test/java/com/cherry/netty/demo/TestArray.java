package com.cherry.netty.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;

import com.cherry.netty.utils.JsonUtil;
import com.cherry.netty.utils.ObjectUtils;

import io.netty.util.internal.ObjectUtil;

public class TestArray {
	 private static final int[] SIZE_TABLE;

	    static {
	        List<Integer> sizeTable = new ArrayList<Integer>();
	        for (int i = 16; i < 512; i += 16) {
	            sizeTable.add(i);
	        }

	        for (int i = 512; i > 0; i <<= 1) {
	            sizeTable.add(i);
	        }

	        SIZE_TABLE = new int[sizeTable.size()];
	        for (int i = 0; i < SIZE_TABLE.length; i ++) {
	            SIZE_TABLE[i] = sizeTable.get(i);
	        }
	    }
	    public static void main(String[] args)  {
			/*for (int i = 0; i < SIZE_TABLE.length; i++) {
				System.out.print(i+"--->"+SIZE_TABLE[i]+"	");
				if((i+1)%5==0){
					System.out.println();
				}
			}*/
	    	int num = 4554;
	    	System.out.println(isPalindrome2(num));
	    	
		}
	    public static boolean isPalindrome2(int x) {
	    	boolean result = false;
	    	int numInput = x;
	    	int num = 0;
	    	int dig = 0;
			if(x > 0){
				while(x > 0 ){
					dig = x%10;
					num = num*10+dig;
					x = x / 10;
				}
				if (num == numInput) {
					result = true;
				}
			}
	        return result;
	    }
	    
	    
	    /**
	     * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
	     */
	    public static boolean isPalindrome(int x) {
	    	boolean result = false;
			try {
				StringBuffer sb = new StringBuffer(String.valueOf(x));
				int num = Integer.valueOf(sb.reverse().toString());
				if (num == x) {
					 result = true;;
				}
			} catch (NumberFormatException e) {
				return false;
			}	
	        return result;
	    }
	    
	    public static int reverse2(int x) {
	    	int symbol = x > 0 ? 1 : -1;
	    	int result = 1;
	    	x = x * symbol;
	    	int len = String.valueOf(x).length();
	    	String strReverse = new String();
	    	try {
				for (int i = 1; i < len; i++) {
					strReverse += (x % 10);
					x = (x/10)==0?(x % 10):(x/10);
				}
				strReverse += x;
				result = Integer.valueOf(strReverse);
				result = result*symbol;
			} catch (NumberFormatException e) {
				result = 0;
			}
	    	return result;
	    }
	    public static int reverse(int x) {
	    	int symbol = x > 0 ? 1 : -1;
	    	int result = 1;
	    	x = x * symbol;
	    	try {
				String strX = String.valueOf(x);
				String strReverse = new String();
				for (int i = 0; i < strX.length(); i++) {
					strReverse += (strX.charAt(strX.length()-1-i));
				}
				result = Integer.valueOf(strReverse);
				result = result*symbol;
			} catch (NumberFormatException e) {
				result = 0;
				e.getStackTrace();
			}
	    	return result;
	    }
	    /*
	     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
	     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
	     */
	    public static int[] twoSum(int[] nums, int target) {
	    	int [] result = null;
	        for(int i= 0; i<nums.length-1; i++){
	        	for(int j=i+1; j<nums.length; j++){
        			if(nums[i]+nums[j]==target){
        				result = new int[]{i,j};
        				break;
        			}
	        	}
	        	if(result != null){
	        		break;
	        	}
	        }
	        return result;
	    }

}
