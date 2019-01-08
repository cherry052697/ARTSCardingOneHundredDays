package com.cherry.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class TestTop100LikedQuestions {
	public static void main(String[] args) {

		TestTop100LikedQuestions test = new TestTop100LikedQuestions();
		
		char[][] board = {
				{'A','B','C','E'},
				{'S','F','C','S'},
				{'A','D','E','E'}};
		String word = "ABCCED";
		System.out.println(test.exist(board, word));
		
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
