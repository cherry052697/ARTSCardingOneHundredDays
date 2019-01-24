package com.cherry.senior;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.cherry.netty.utils.ObjectUtils;
import com.cherry.netty.utils.StringUtil;


public class TestString {
	public static void main(String[] args) throws Exception {
		String str = "第一行 你好，\n第二行 我好\r第三行 大家好\thahahahahah ";
		System.out.println(str);
		String str2 = str.replaceAll("\n", " ");
		System.out.println("str2\n"+str2);
		String str3 = str.replaceAll("\r", " ");
		System.out.println("str3\n"+str3);
		String str4 = str.replaceAll("\t", " ");
		System.out.println("str4\n"+str4);
		String str5 = replaceBlank(str);
		System.out.println("\nstr5\n"+str5);
		System.out.println("===========");
		System.out.println(StringUtil.replaceBlank(str, "\n|\r|\t"));
	}
	
	public static String  replaceBlank(String source){
		String dest = "";
		if (!ObjectUtils.isEmpty(source)) {
			Pattern p = Pattern.compile("\t|\r|\n");
			Matcher m = p.matcher(source);
			dest = m.replaceAll("");
		}
		return dest;
	}
	


}
