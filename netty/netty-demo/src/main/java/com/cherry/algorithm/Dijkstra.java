package com.cherry.algorithm;

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
	
	
	void dijkstra(Vertex s){
		
	}
	
	
	void printPath(Vertex v) {
		if (v.path != null) {
			printPath(v.path);
			System.out.println(" to ");
		}
		System.out.println(v);
	}

	
}
