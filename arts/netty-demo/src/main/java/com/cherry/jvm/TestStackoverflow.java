package com.cherry.jvm;

public class TestStackoverflow {
	public static void main(String[] args) {
	    long startTime = System.nanoTime();
	    int n = 0;
	    for (int i = 0; i < 1000000000; i++) {
	        n += 2 * i * i;
	    }
	    System.out.println((double) (System.nanoTime() - startTime) / 1000000000 + " s");
	    System.out.println("n = " + n);
	}

}
