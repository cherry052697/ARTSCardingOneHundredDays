package com.cherry.leetcode;

import java.util.ArrayList;
import java.util.List;

import com.cherry.netty.utils.JsonUtil;

public class DailyExerciseAlgorithms {
	public static void main(String[] args) {
		DailyExerciseAlgorithms deca = new DailyExerciseAlgorithms();
		System.out.println(JsonUtil.toJson(deca.hundredDollarsForHundredChickens()));
	}

	/*
	 * 1、百钱买百鸡的问题算是一套非常经典的不定方程的问题，题目很简单：公鸡5文钱一只，母鸡3文钱一只，小鸡3只一文钱，
	 * 用100文钱买一百只鸡,其中公鸡，母鸡，小鸡都必须要有，问公鸡，母鸡，小鸡要买多少只刚好凑足100文钱
	 */
	public List<int[]> hundredDollarsForHundredChickens() {
		// re[0]-公鸡数；res[1]—母鸡；res[2]-小鸡
		int[] res = { 0, 0, 0 };
		List<int[]> result = new ArrayList<int[]>();
		for (int cock = 1; cock < 20; cock++) {// 公鸡
			for (int hen = 1; hen < 33; hen++) {// 母鸡
				int chicken = 100 - cock - hen;
				if (5 * cock + 3 * hen + chicken / 3 == 100 && chicken % 3 == 0) {
					res = new int[3];
					res[0] = cock;
					res[1] = hen;
					res[2] = chicken;
					result.add(res);
				}
			}
		}
		return result;
	}

	/*
	 * 2、古代数学巨著《九章算数》中有这么一道题叫“五家共井，甲二绠（汲水用的井绳）不足，如（接上）乙一绠；乙三绠不足，如丙一绠；
	 * 丙四绠不足，如丁一绠；丁五绠不足，如戊一绠；戊六绠不足，如甲一绠，皆及。
	 * 意思就是说五家人共用一口井，甲家的绳子用两条不够，还要再用乙家的绳子一条才能打到井水；乙家的绳子用三条不够，还要再用丙家的绳子
	 * 一条才能打到井水；丙家的绳子用四条不够，还要再用丁家的绳子一条才能打到井水；丁家的绳子用五条不够，还要再用戊家的绳子一条才能打
	 * 到井水；戊家的绳子用六条不够，还要再用甲家的绳子一条才能打到井水。 最后问：井有多深？每家的绳子各有多长？
	 */
	
	/*
	 * 3、猴子第一天摘下若干个桃子，当即吃了一半，还不过瘾就多吃了一个。第二天早上又将剩下的桃子吃了一半，还是不过瘾又多
	 * 吃了一个。以后每天都吃前一天剩下的一半再加一个。到第10天刚好剩一个。问猴子第一天摘了多少个桃子？
	 */
	
	/*
	 * 4、最长公共子序列的问题常用于解决字符串的相似度，是一个非常实用的算法，作为码农，此算法是我们的必备基本功。
	 */
	
	/*
	 * 5、这篇我们看看最长公共子序列的另一个版本，求字符串相似度(编辑距离)，我也说过了，这是一个非常实用的算法，在DNA对比，网
	 * 
	 * 页聚类等方面都有用武之地。 一：概念
	 * 
	 * 对于两个字符串A和B，通过基本的增删改将字符串A改成B，或者将B改成A，在改变的过程中我们使用的最少步骤称之为“编辑距离”。
	 * 
	 * 比如如下的字符串：我们通过种种操作，痉挛之后编辑距离为3，不知道你看出来了没有？
	 */

}
