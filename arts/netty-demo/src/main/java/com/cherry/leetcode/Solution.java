package com.cherry.leetcode;

import java.util.ArrayList;
import java.util.Iterator;

public class Solution {
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
}