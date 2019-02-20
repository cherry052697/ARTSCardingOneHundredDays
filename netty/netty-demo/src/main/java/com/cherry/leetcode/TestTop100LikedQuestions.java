package com.cherry.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.cherry.netty.utils.JsonUtil;




public class TestTop100LikedQuestions {
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		Top100LikedQuestions test = new Top100LikedQuestions();
		
		/*char[][] board = {
				{'A','B','C','E'},
				{'S','F','C','S'},
				{'A','D','E','E'}};
		String word = "ABCCED";
		System.out.println(test.exist(board, word));*/

		ListNode node = new ListNode(1);
		node.next=new ListNode(2);
		node.next.next = new ListNode(3);
		node.next.next.next = new ListNode(4);
		node.next.next.next.next = new ListNode(5);
		node.next.next.next.next.next = null;
//		System.out.println(node);
//		System.out.println(test.reverseList(node));
		int[] nums = {1,2,3,1};
//		System.out.println(test.maxProfit(nums));
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(3);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
//		System.out.println(test.diameterOfBinaryTree(root));
//		System.out.println(test.rob(nums));
		ListNode head = new ListNode(1);
		ListNode head2 = new ListNode(1);
		head.next = head2;
//		System.out.println(head);
//		System.out.println(test.isPalindrome2(head));
		int [] heights = {2,1,5,6,2,3};
//		System.out.println(test.largestRectangleArea2(heights));
		
//		System.out.println(new TestTop100LikedQuestions().reverseListNode(head));
		
		TreeNode rootSymmetric = new TreeNode(10);
		rootSymmetric.right = new TreeNode(-3);
		rootSymmetric.right.right = new TreeNode(11);
		rootSymmetric.left = new TreeNode(5);
		rootSymmetric.left.left = new TreeNode(3);
		rootSymmetric.left.left.left = new TreeNode(3);
		rootSymmetric.left.left.right = new TreeNode(-2);
		rootSymmetric.left.right = new TreeNode(2);
		rootSymmetric.left.right.right = new TreeNode(1);

		TreeNode s = new TreeNode(3);
		s.left = new TreeNode(4);
		s.right = new TreeNode(5);
		s.left.left = new TreeNode(1);
		s.left.right = new TreeNode(2);
		s.left.right.left = new TreeNode(0);
		
		TreeNode t = new TreeNode(4);
		t.left = new TreeNode(1);
		t.right = new TreeNode(2);
		
		String palindrome = "";
//		System.out.println(test.longestPalindrome6(palindrome));
		
		int [][] people ={{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
//		System.out.println(JsonUtil.toJson(test.reconstructQueue(people)));
		
		int [] numsProduct = {2,3,-2,4};
//		System.out.println(test.maxProduct(numsProduct));
		
		ListNode n1 = new ListNode(2);
		n1.next = new ListNode(4);
		n1.next.next = new ListNode(3);
		ListNode n2 = new ListNode(5);
		n2.next = new ListNode(6);
		n2.next.next = new ListNode(4);
//		System.out.println(n1);
//		System.out.println(n2);
//		System.out.println(test.addTwoNumbers(n1, n2));
		ListNode n3 = new ListNode(9);
		n3.next = new ListNode(8);
		ListNode n4 = new ListNode(1);
//		System.out.println(n3);
//		System.out.println(n4);
//		System.out.println(test.addTwoNumbers(n3, n4));
		ListNode n5 = new ListNode(9);
		ListNode n6 = new ListNode(1);
		n6.next = new ListNode(9);
		n6.next.next = new ListNode(9);
		n6.next.next.next = new ListNode(9);
		n6.next.next.next.next = new ListNode(9);
		n6.next.next.next.next.next = new ListNode(9);
		n6.next.next.next.next.next.next = new ListNode(9);
		n6.next.next.next.next.next.next.next = new ListNode(9);
		n6.next.next.next.next.next.next.next.next = new ListNode(9);
		n6.next.next.next.next.next.next.next.next.next = new ListNode(9);
//		System.out.println(test.addTwoNumbers(n5, n6));
		
		char[][] matrix = {{'1','0','1','0','0'},
						   {'1','0','1','1','1'},
						   {'1','1','1','1','1'},
						   {'1','0','0','1','0'}};
//		System.out.println(test.maximalSquare3(matrix));
		int[][] matrixInt = {
							{1, 4,  7, 11, 15},
							{2, 5,  8, 12, 19},
							{3, 6,  9, 16, 22},
							{10,13, 14,17, 24},
							{18,21, 23,26, 30}};
		int[][] matrixInt2 = {{-5},{-2}};
//		System.out.println(test.searchMatrix(matrixInt2, -2));
		
//		String str = "abac";
//		System.out.println(test.countSubstrings3(str));
		
//		int[] area = {1,8,6,2,5,4,8,3,7};
//		System.out.println(test.maxArea(area));
		
		/*ListNode listnode = new ListNode(1);
		listnode.next = new ListNode(2);
		listnode.next.next = new ListNode(3);
		listnode.next.next.next = new ListNode(4);
		listnode.next.next.next.next = new ListNode(5);*/
//		System.out.println(test.removeNthFromEnd3(listnode, 2));
		
//		String phoneNumber = "21";
//		System.out.println(JsonUtil.toJson(test.letterCombinations(phoneNumber)));
//		System.out.println(phoneNumber.hashCode());
//		char [] value = new char[phoneNumber.length()];
//		phoneNumber.getChars(0, phoneNumber.length(), value, 0);
//		System.out.println(JsonUtil.toJson(value));
//		System.out.println(new TestTop100LikedQuestions().hashCode(value));

		/*  ListNode list1 = new ListNode(1);
		  list1.next = new ListNode(4);
		  list1.next.next = new ListNode(5);
		  ListNode list2 = new ListNode(1);
		  list2.next = new ListNode(3);
		  list2.next.next = new ListNode(4);
		  ListNode list3 = new ListNode(2);
		  list3.next = new ListNode(6);*/
//		ListNode [] lists = {list1,list2,list3};
//		System.out.println(test.mergeKLists(lists));
		
//		int[] arr = {2,3,1,1,4};
//		System.out.println(test.canJump(arr));
//		String s1 = "aab",p1 = "c*a*b";
//		System.out.println(test.isMatch4(s1, p1));
//		int [] nums2 = {1,2,3};
//		test.nextPermutation(nums2);
//		int [] nums3 = {2,3,5};
//		System.out.println(JsonUtil.toJson(test.combinationSum(nums3, 8)));
//		int [] nums4 = {3,4,7,2,-3,1,4,2};
//		System.out.println(test.subarraySum(nums4, 7));
		int [] nums5 = {1,2,3,4};
//		System.out.println(test.coinChange3(nums5, 11));
//		test.productExceptSelf2(nums5);
		int [] nums6 = {1, 5, 11, 5};
//		System.out.println(test.canPartition3(nums6));
		String deStr = "3[a]2[b4[F]c]";
//		System.out.println(test.decodeString2(deStr));
		int[] nums7 = {1,1,1,2,2,3};
//		System.out.println(JsonUtil.toJson(test.topKFrequent3(nums7, 2)));
//		4->2->1->3
		ListNode headNode = new ListNode(4);
		headNode.next = new ListNode(2);
		headNode.next.next=new ListNode(1);
		headNode.next.next.next = new ListNode(3);
//		System.out.println(JsonUtil.toJson(test.sortList(headNode)));
		TreeNode root1 = new TreeNode(1);
		root1.left = null;
		root1.right = new TreeNode(2);
		root1.right.left = new TreeNode(3);
//		System.out.println(JsonUtil.toJson(test.inorderTraversal(root1)));
		int[][] grid = {{1,3,1},
		                 {1,5,1},
		                 {4,2,1}};
//		System.out.println(test.minPathSum2(grid));
		int[] nums8 = {1,2,3};
//		System.out.println(JsonUtil.toJson(test.subsets(nums8)));
		int [] nums9 ={1,4,6,6,6,2,3};
//		System.out.println(test.findDuplicate3(nums9));
		TreeNode lroot = new TreeNode(3);
		lroot.left = new TreeNode(9);
		lroot.right = new TreeNode(20);
		lroot.right.left = new TreeNode(15);
		lroot.right.right = new TreeNode(7);
//		System.out.println(JsonUtil.toJson(test.levelOrder2(lroot)));
		int[] nums10 = {3,2,3,1,2,4,5,5,6};
//		System.out.println(test.findKthLargest(nums10, 4));
		String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
//		System.out.println(JsonUtil.toJson(test.groupAnagrams2(strs)));
		TreeNode rootf = new TreeNode(1);
		rootf.left = new TreeNode(2);
		rootf.left.left = new TreeNode(3);
		rootf.left.right = new TreeNode(4);
		rootf.right = new TreeNode(5);
		rootf.right.right= new TreeNode(6);
		test.flatten(rootf);
		
		
		
		
	}
	public  int hashCode(char [] value) {
        int h = 0;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
        }
        return h;
    }
	
	 private class Pair{
	        public int x;
	        public int y;
	        public Pair(int inX, int inY){
	            x=inX;
	            y=inY;
	        }
	    }
	    
	    private ArrayList<Pair> findChar(char[][] board, int[][] matrix, char c, int x, int y){
	        int row=board.length;
	        int col=board[0].length;
	        
	        ArrayList<Pair> list= new ArrayList<Pair>();
	        
	        if(x==-1 && y==-1){
	            for(int i=0; i<row; i++){
	                for(int j=0; j<col; j++){
	                    if(board[i][j]==c){
	                        list.add(new Pair(i,j));
	                        //matrix[i][j]=1;
	                    }
	                }
	            }
	        }else{
	            //top
	            if(x-1>=0){
	                if(board[x-1][y]==c && matrix[x-1][y]==0){
	                    list.add(new Pair(x-1, y));
	                    //matrix[x-1][y]=1;
	                }
	            }
	            
	            //bottom
	            if(x+1<=row-1){
	                if(board[x+1][y]==c && matrix[x+1][y]==0){
	                    list.add(new Pair(x+1,y));
	                    //matrix[x+1][y]=1;
	                }
	            }
	            
	            //left
	            if(y-1>=0){
	                if(board[x][y-1]==c && matrix[x][y-1]==0){
	                    list.add(new Pair(x, y-1));
	                    //matrix[x][y-1]=1;
	                }
	            }
	            
	            //right
	            if(y+1<=col-1){
	                if(board[x][y+1]==c && matrix[x][y+1]==0){
	                    list.add(new Pair(x, y+1));
	                    //matrix[x][y+1]=1;
	                }
	            }
	        }
	        
	        return list;
	    }
	    
	    private boolean process(char[][] board, int[][] matrix, String word, int x, int y, ArrayList<Pair> preList){
	        boolean ans=false;
	        int row=board.length;
	        int col=board[0].length;
	        
	        if(word.length()==0){
	            return true;
	        }
	    
	        ArrayList<Pair> list=findChar(board, matrix, word.charAt(0), x, y);
	        if(list.isEmpty()){
	            return false;
	        }
	        
	        for(Iterator<Pair> iter=list.iterator(); iter.hasNext();){
	            Pair p= iter.next();
	            if(ans){
	                return true;
	            }
	            
	            int[][] mTmp=new int[row][col];
	            for(Iterator<Pair> iter2=preList.iterator(); iter2.hasNext();){
	                Pair p2=iter2.next();
	                mTmp[p2.x][p2.y]=1;
	            }
	            mTmp[p.x][p.y]=1;
	            
	            ArrayList<Pair> tList=new ArrayList<Pair>(preList);
	            tList.add(p);
	            ans=process(board, mTmp, word.substring(1), p.x, p.y, tList);
	        }
	        
	        return ans;
	    }
	    
	    public boolean exist(char[][] board, String word) {
	        int row=board.length;
	        int col=board[0].length;
	        int[][] mTmp= new int[row][col];
	        
	        ArrayList<Pair> tList=new ArrayList<Pair>();
	        return process(board, mTmp, word, -1, -1, tList);
	    }
	
	
	public List<Integer> findAnagrams2(String s, String p) {
		List<Integer> result = new ArrayList<>();
		if (s == null || s.length() == 0 || p == null || p.length() == 0)
			return result;
		int[] hash = new int[256];
		char[] pp = p.toCharArray();
		for (char i : pp) {
			hash[i]++;
		}
		int left = 0, right = 0, count = p.length();
		while (right < s.length()) {
			if (hash[s.charAt(right++)]-- > 0) // 窗口右移；相应的hash值减小；如果这个位置的Hash值是正的，表示p字符串也包含这个，所以count做减法
				count--;
			if (count == 0)
				result.add(left);// count指示器，为0表示和p对应的hash值完全一致
			if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0)
				// 如果当窗口大小一定的时候即窗口大小和需要比较的字符串大小一致的时候，将窗口左边的指针向右边移动，移动的同时左边的字符计数因为在第一个if的地方hash值减小过，所以需要执行对应恢复操作，即：hash值增加，count计数值增加。
				count++;
		}
		return result;

	}

	List<String> showCombResult = new ArrayList<String>();
	int listSize = 0;

	@SuppressWarnings("rawtypes")
	public List<Integer> findAnagrams(String s, String p) {
		listSize = p.length();
		Set<Integer> result = new TreeSet<Integer>();
		if (s.length() >= p.length() && s.length() <= 20100 && p.length() <= 20100) {
			String[] array = p.split("");
			showComb(Arrays.asList(array), "");
			for (Iterator iterator = showCombResult.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				if (s.contains(string)) {
					for (int i = 0; i < s.length(); i++) {
						if (i + string.length() <= s.length()) {
							String temp = s.substring(i, i + string.length());
							if (temp.equalsIgnoreCase(string)) {
								result.add(i);
							}
						}
					}
				}
			}

		}
		return new ArrayList<>(result);
	}

	public void showComb(List<String> list, String prefix) {
		if (prefix.length() == listSize) {
			showCombResult.add(prefix);
		}
		for (int i = 0; i < list.size(); i++) {
			List<String> tmp = new LinkedList<String>(list);
			showComb(tmp, prefix + tmp.remove(i));
		}
	}

}
