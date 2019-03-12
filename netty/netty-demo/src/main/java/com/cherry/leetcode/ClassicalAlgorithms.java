package com.cherry.leetcode;

import java.util.ArrayList;
import java.util.Collections;

import com.cherry.netty.utils.JsonUtil;

public class ClassicalAlgorithms {

	/*
	 * 二分查找
	 * 
	 * 又叫折半查找，要求待查找的序列有序。每次取中间位置的值与待查关键字比较，如果中间位置
	 * 的值比待查关键字大，则在前半部分循环这个查找的过程，如果中间位置的值比待查关键字小，
	 * 则在后半部分循环这个查找的过程。直到查找到了为止，否则序列中没有待查的关键字
	 */
	public int binarySearch(int[] array, int a) {
		int left = 0, right = array.length - 1, middle;
		while (left <= right) {
			middle = (left + right) / 2;
			if (array[middle] == a)
				return middle + 1;
			else if (array[middle] < a)
				left = middle + 1;
			else
				right = middle - 1;

		}
		return -1;
	}
	/*
	 * 冒泡 排序 算法
	 * 
	 * （1）比较前后相邻的二个数据，如果前面数据大于后面的数据，就将这二个数据交换。 （2）这样对数组的第 0 个数据到
	 * N-1个数据进行一次遍历后，最大的一个数据就“沉”到数组第 N-1 个位置。 （3）N=N-1，如果 N 不为 0 就重复前面二步，否则排序完成。
	 */

	public void bubbleSort1(int[] a, int n) {
		int i, j;
		for (i = 0; i < n; i++) {
			for (j = 1; j < n - i; j++) {
				if (a[j - 1] > a[j]) {
					int temp = a[j - 1];
					a[j - 1] = a[j];
					a[j] = temp;
				}
			}
		}

	}

	/*
	 * 插入 排序 算法
	 * 
	 * 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应的位置并插入。
	 * 插入排序非常类似于整扑克牌。在开始摸牌时，左手是空的，牌面朝下放在桌上。接着，一次从
	 * 桌上摸起一张牌，并将它插入到左手一把牌中的正确位置上。为了找到这张牌的正确位置，要将
	 * 它与手中已有的牌从右到左地进行比较。无论什么时候，左手中的牌都是排好序的。
	 * 如果输入数组已经是排好序的话，插入排序出现最佳情况，其运行时间是输入规模的一个线性函
	 * 数。如果输入数组是逆序排列的，将出现最坏情况。平均情况与最坏情况一样，其时间代价是(n2)
	 */

	public void insertSort(int arr[]) {
		for (int i = 1; i < arr.length; i++) {
			int insertVal = arr[i];
			int index = i - 1;
			while (index >= 0 && insertVal < arr[index]) {
				arr[index + 1] = arr[index];
				index--;
			}
			arr[index + 1] = insertVal;
			// System.out.println("i="+i+","+JsonUtil.toJson(arr));
		}

	}

	/*
	 * 快速排序 算法
	 * 
	 * 快速排序的原理：选择一个关键值作为基准值。比基准值小的都在左边序列（一般是无序的），
	 * 比基准值大的都在右边（一般是无序的）。一般选择序列的第一个元素。
	 * 一次循环：从后往前比较，用基准值和最后一个值比较，如果比基准值小的交换位置，如果没有
	 * 继续比较下一个，直到找到第一个比基准值小的值才交换。找到这个值之后，又从前往后开始比
	 * 较，如果有比基准值大的，交换位置，如果没有继续比较下一个，直到找到第一个比基准值大的
	 * 值才交换。直到从前往后的比较索引>从后往前比较的索引，结束第一次循环，此时，对于基准值 来说，左右两边就是有序的了。
	 */
	public void quickSort(int[] a, int low, int high) {
		int start = low, end = high;
		int key = a[low];
		while (end > start) {
			// 从后往前比较,找到首个小于key的(右边)索引作为end
			while (end > start && a[end] >= key)
				end--;
			// a[end] <= key时,交换a[end]←→a[start]
			if (a[end] <= key) {
				int temp = a[end];
				a[end] = a[start];
				a[start] = temp;
			}
			// 从前往后比较,找到首个大于key的(左边)索引
			while (end > start && a[start] <= key)
				start++;
			// a[start] >= key时,交换a[end]←→a[start]
			if (a[start] >= key) {
				int temp = a[start];
				a[start] = a[end];
				a[end] = temp;
			}
			System.out.println("start=" + start + ",end=" + end + "——" + JsonUtil.toJson(a));
		}
		if (start > low)
			quickSort(a, low, start - 1);
		if (end < high)
			quickSort(a, end + 1, high);

	}

	/*
	 * 希尔排序算法（递减增量法）
	 * 
	 * 基本思想：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列 中的记录“基本有序”时，再对全体记录进行依次直接插入排序。
	 * 1. 操作方法： 选择一个增量序列 t1，t2，…，tk，其中 ti>tj，tk=1； 2. 按增量序列个数 k，对序列进行 k 趟排序； 3.
	 * 每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，分别对各子表进 行直接插入排序。仅增量因子为1
	 * 时，整个序列作为一个表来处理，表长度即为整个序列的长 度。
	 */
	public void shellSort(int[] a) {
		int dk = a.length / 2;// 步长
		while (dk >= 1) {
			shellInsertSort(a, dk);
			dk = dk >> 1;
		}

	}

	private void shellInsertSort(int[] a, int dk) {
		for (int i = dk; i < a.length; i++) {
			if (a[i] < a[i - dk]) {
				int j, x = a[i];
				a[i] = a[i - dk];
				for (j = i - dk; j >= 0 && x < a[j]; j = j - dk)
					a[j + dk] = a[j];
				a[j + dk] = x;
			}
		}
		System.out.println(JsonUtil.toJson(a));
	}

	/*
	 * 归并 排序 算法
	 * 
	 * 归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表，即把待排序序列
	 * 分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列。
	 */

	public void mergeSort(int[] data) {
		sort(data, 0, data.length - 1);
	}

	public void sort(int[] data, int left, int right) {
		if (left >= right)
			return;
		int center = (left + right) / 2;
		sort(data, left, center);
		sort(data, center + 1, right);
		merge(data, left, center, right);
	}

	private void merge(int[] data, int left, int center, int right) {
		int[] tmpArr = new int[data.length];
		int mid = center + 1;
		int third = left;
		int tmp = left;
		while (left <= center && mid <= right) {
			// 从链各个数组中取出最小的放入临时数组
			if (data[left] <= data[mid]) {
				tmpArr[third++] = data[left++];
			} else {
				tmpArr[third++] = data[mid++];
			}
		}
		// 剩余部分依次放入临时数组
		while (mid <= right) {
			tmpArr[third++] = data[mid++];
		}
		while (left <= center) {
			tmpArr[third++] = data[left++];
		}
		// 将临时数组中的内容拷贝回原数组中
		while (tmp <= right) {
			data[tmp] = tmpArr[tmp++];
		}
	}

	/*
	 * 桶排序算法
	 * 
	 * 桶排序的基本思想是： 把数组 arr 划分为 n 个大小相同子区间（桶），每个子区间各自排序，最 后合并。
	 * 计数排序是桶排序的一种特殊情况，可以把计数排序当成每个桶里只有一个元素的情况。
	 * 
	 * 1.找出待排序数组中的最大值 max、最小值 min 2.我们使用 动态数组 ArrayList 作为桶，桶里放的元素也用 ArrayList
	 * 存储。桶的数量为(max-min)/arr.length+1 3.遍历数组 arr，计算每个元素 arr[i] 放的桶 4.每个桶各自排序
	 */
	public void bucketSort(int[] arr) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(arr[i], max);
			min = Math.min(arr[i], min);
		}
		// 创建桶
		int bucketNum = (max - min) / arr.length + 1;
		ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<ArrayList<Integer>>(bucketNum);
		for (int i = 0; i < bucketNum; i++) {
			bucketArr.add(new ArrayList<Integer>());
		}
		// 将每个元素放入桶里
		for (int i = 0; i < arr.length; i++) {
			int num = (arr[i] - min) / arr.length;
			bucketArr.get(num).add(arr[i]);
		}
		for (int i = 0; i < bucketArr.size(); i++) {
			Collections.sort(bucketArr.get(i));
		}
		System.out.println(JsonUtil.toJson(bucketArr));

	}

	/*
	 * 基数排序算法
	 * 
	 * 
	 */

	/*
	 * 最短路径算法
	 */

	/*
	 * 最大子数组算法
	 */
	int maxSubArray(int[] array, int length) {
		int boundry = array[0];
		int maxArray = array[0];
		for (int i = 1; i < length; ++i) {
			if (boundry + array[i] >= array[i])
				boundry += array[i];
			else
				boundry = array[i];
			if (maxArray < boundry)
				maxArray = boundry;
		}
		return maxArray;
	}

	/*
	 * 最长公共子序算法
	 */
	public int longestCommonSubsequence(String A, String B) {
		int n = A.length();
		int m = B.length();
		int f[][] = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
				if (A.charAt(i - 1) == B.charAt(j - 1))
					f[i][j] = f[i - 1][j - 1] + 1;
			}
		}
		return f[n][m];
	}

	public static int longestCommonSubsequence2(String A, String B) {
		// state: f[i][j] is the length of the longest lcs
		// ended with A[i - 1] & B[j - 1] in A[0..i-1] & B[0..j-1]
		int m = A.length();
		int n = B.length();
		// （由于任何str与空串的LCS都为零：故将第0行/列全部置0！（默认初始化就是0））
		// 因此 分别以A、B为行、列拼成的棋盘chess，行数列数均需+1（<-第0行/列）
		int chess[][] = new int[m + 1][n + 1];
		int i, j;
		// Stack<Character> stack = null;
		StringBuffer sb = new StringBuffer();

		// 从第1行/列开始数。
		for (i = 1; i <= m; i++) {
			for (j = 1; j <= n; j++) {
				// 由于i、j均为1起算，故用i、j表示字符串下标时，需要减1（而棋盘chess不需要考虑这个，因为第0行/列本就算做另加的）
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					chess[i][j] = chess[i - 1][j - 1] + 1;
				} else {
					chess[i][j] = 0;
				}
			}
		}

		// 求最长公共子序列的长度
		int max = 0;
		for (i = 1; i <= m; i++) {
			for (j = 1; j <= n; j++) {
				max = Math.max(max, chess[i][j]);
			}
		}
		// 棋盘chess中的值已设置完成。

		// 将i、j下标落到末尾元素上。（以之为起点，一直走到头）
		i = m;
		j = n;
		// 求LCS解（之一）
		try {
			while (i != 0 && j != 0) {// 当i、j到达第0行/列时，棋盘走到尽头。
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					// System.out.println("i: "+i+", j: "+j+" "+A.charAt(i -
					// 1)+" ; "+B.charAt(j - 1)); //Debug
					sb.append(A.charAt(i - 1));// 将相同的子元素压栈。然后指针前移，直到i、j指向0终止(因为任何字符串
												// 与0 求公共子序列，都是0)
					i--;
					j--;
				} else { // 若二者不相等，而最长公共子序列一定是由LCS（chess[i][j-1] or
							// chess[i-1][j]）的较大者得来，故将较大者的指针前移，接着遍历。
					if (chess[i][j - 1] > chess[i - 1][j]) {
						j--;
					} else { // if(chess[i][j-1] <= chess[i-1][j])
						i--;
					}

				}
			}
			System.out.println("One of the Longest Common Subsequence: " + sb.reverse());
		} catch (NullPointerException e) {
			System.err.println("NullPointerException!");
		}
		return max;
	}

	/*
	 * 最小生成树算法
	 */

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ClassicalAlgorithms ca = new ClassicalAlgorithms();
		int[] arr = { 1, 2, 3, 5, 6, 8, 12, 13, 18 };
		// System.out.println(ca.binarySearch(arr, 8));
		int[] arr2 = { 4, 9, 3, 7, 6, 8, 1, 13 };
		// ca.bubbleSort1(arr2, 8);
		// System.out.println(JsonUtil.toJson(arr2));
		int[] arrs3 = { 49, 38, 65, 97, 76, 13, 27, 49, 55, 04 };
		// ca.insertSort(arrs3);
		// ca.quickSort(arrs3, 0, arrs3.length - 1);
		ca.shellSort(arrs3);
		// ca.mergeSort(arrs3);
		// ca.bucketSort(arrs3);
		System.out.println(JsonUtil.toJson(arrs3));

	}

}
