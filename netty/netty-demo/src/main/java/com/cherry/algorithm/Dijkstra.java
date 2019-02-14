package com.cherry.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Dijkstra {
	
	
	/**
	 * 词梯游戏
	 * @param adjacentWords
	 * @param first
	 * @param second
	 * @return
	 */
	public static List<String> findChain(Map<String, List<String>> adjacentWords,String first,String second){
		Map<String, String> previousWord = new HashMap<String, String>();
		LinkedList<String> q = new LinkedList<String>();
		q.addLast(first);
		while (!q.isEmpty()) {
			String current = q.removeFirst();
			List<String> adj = adjacentWords.get(current);
			if (adj != null) {
				for (String adjWord:adj) {
					if (previousWord.get(adjWord)==null) {
						previousWord.put(adjWord, current);
						q.addLast(adjWord);
					}
				}
			}
		}
		previousWord.put(first, null);
		return getChainFromPreviousMap(previousWord, first, second);
	}
	
	public static List<String> getChainFromPreviousMap(Map<String, String> prev,String first,String second){
		LinkedList<String> result = null;
		if (prev.get(second)!=null) {
			result = new LinkedList<String>();
			for(String str=second;str!=null;str=prev.get(str))
				result.addFirst(str);
		}
		return result;
	}
	
	public static void main(String[] args) {
	        List<Vertex> vertexs = new ArrayList<Vertex>();
	        Vertex a = new Vertex("A", 0);
	        Vertex b = new Vertex("B");
	        Vertex c = new Vertex("C");
	        Vertex d = new Vertex("D");
	        Vertex e = new Vertex("E");
	        Vertex f = new Vertex("F");
	        vertexs.add(a);
	        vertexs.add(b);
	        vertexs.add(c);
	        vertexs.add(d);
	        vertexs.add(e);
	        vertexs.add(f);
	        int[][] edges = {
	                {Integer.MAX_VALUE,6,3,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
	                {6,Integer.MAX_VALUE,2,5,Integer.MAX_VALUE,Integer.MAX_VALUE},
	                {3,2,Integer.MAX_VALUE,3,4,Integer.MAX_VALUE},
	                {Integer.MAX_VALUE,5,3,Integer.MAX_VALUE,5,3},
	                {Integer.MAX_VALUE,Integer.MAX_VALUE,4,5,Integer.MAX_VALUE,5},
	                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,3,5,Integer.MAX_VALUE}
	        
	        };
	        Graph graph = new Graph(vertexs, edges);
	        graph.printGraph();
	        graph.search();
	    }
	
}
