package com.example.demo;

public class TestException {
	public static void main(String[] args) {
		boolean flag = true;
		try {
			flag = false;
			int num = 5/0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(flag);
	}

}
