package com.cherry.leetcode;

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

	public void sort(int arr[]) {

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
	public void sort(int[] a, int low, int high) {

	}

	/*
	 * 希尔排序算法
	 * 
	 * 基本思想：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列 中的记录“基本有序”时，再对全体记录进行依次直接插入排序。
	 * 1. 操作方法： 选择一个增量序列 t1，t2，…，tk，其中 ti>tj，tk=1； 2. 按增量序列个数 k，对序列进行 k 趟排序； 3.
	 * 每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，分别对各子表进 行直接插入排序。仅增量因子为1
	 * 时，整个序列作为一个表来处理，表长度即为整个序列的长 度。
	 */
	private void shellSort(int[] a) {

	}

	/*
	 * 归并 排序 算法
	 * 
	 * 归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表，即把待排序序列
	 * 分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列。
	 */

	public void mergeSort(int[] data) {
		sort2(data, 0, data.length - 1);
	}

	public void sort2(int[] data, int left, int right) {

	}

	/*
	 * 桶排序算法
	 * 
	 * 桶排序的基本思想是： 把数组 arr 划分为 n 个大小相同子区间（桶），每个子区间各自排序，最 后合并
	 * 。计数排序是桶排序的一种特殊情况，可以把计数排序当成每个桶里只有一个元素的情况。 1.找出待排序数组中的最大值 max、最小值 min
	 * 2.我们使用 动态数组 ArrayList 作为桶，桶里放的元素也用 ArrayList 存储。桶的数量为(max-
	 * min)/arr.length+1 3.遍历数组 arr，计算每个元素 arr[i] 放的桶 4.每个桶各自排序
	 */
	public void bucketSort(int[] arr) {

	}
	
	/*
	 * 最短路径算法
	 */
	
	/*
	 * 最大子数组算法
	 */
	
	/*
	 * 最长公共子序算法
	 */
	
	/*
	 * 最小生成树算法
	 */
	

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 5, 6, 8, 12, 13, 18 };
		System.out.println(new ClassicalAlgorithms().binarySearch(arr, 8));
	}

}