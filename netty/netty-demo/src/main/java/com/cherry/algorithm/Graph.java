package com.cherry.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph {

	/*
	 * 算法思想
1、令G = （V，E）为一个带权无向图。G中若有两个相邻的节点,i和j。aij(在这及其后面都表示为下标，请注意)为节点i到节点j的权值，在本算法可以理解为距离。每个节点都有一个值di(节点标记)表示其从起点到它的某条路的距离。
2、算法初始有一个数组V用于储存未访问节点的列表，我们暂称为候选列表。选定节点1为起始节点。开始时，节点1的d1=0, 其他节点di=无穷大，V为所有节点。
初始化条件后，然后开始迭代算法，直到V为空集时停止。具体迭代步骤如下：

将d值最小的节点di从候选列表中移除。(本例中V的数据结构采用的是优先队列实现最小值出列，最好使用斐波那契对，在以前文章有过介绍，性能有大幅提示)。对于以该节点为起点的每一条边，不包括移除V的节点, (i, j)属于A， 若dj > di + aij（违反松弛条件）,则令
dj = di + aij    , （如果j已经从V中移除过，说明其最小距离已经计算出，不参与此次计算）

可以看到在算法的运算工程中，节点的d值是单调不增的
	 */
	
    /*
     * 顶点
     */
    private List<Vertex> vertexs;

    /*
     * 边
     */
    private int[][] edges;

    /*
     * 没有访问的顶点
     */
    private Queue<Vertex> unVisited;

    public Graph(List<Vertex> vertexs, int[][] edges) {
        this.vertexs = vertexs;
        this.edges = edges;
        initUnVisited();
    }
    
    /*
     * 搜索各顶点最短路径
     */
    public void search(){
        while(!unVisited.isEmpty()){
            Vertex vertex = unVisited.element();
            //顶点已经计算出最短路径，设置为"已访问"
            vertex.setMarked(true);    
            //获取所有"未访问"的邻居
            List<Vertex> neighbors = getNeighbors(vertex);    
            //更新邻居的最短路径
            updatesDistance(vertex, neighbors);        
            pop();
        }
        System.out.println("search over");
    }
    
    /*
     * 更新所有邻居的最短路径
     */
    private void updatesDistance(Vertex vertex, List<Vertex> neighbors){
        for(Vertex neighbor: neighbors){
            updateDistance(vertex, neighbor);
        }
    }
    
    /*
     * 更新邻居的最短路径
     */
    private void updateDistance(Vertex vertex, Vertex neighbor){
        int distance = getDistance(vertex, neighbor) + vertex.getPath();
        if(distance < neighbor.getPath()){
            neighbor.setPath(distance);
        }
    }

    /*
     * 初始化未访问顶点集合
     */
    private void initUnVisited() {
        unVisited = new PriorityQueue<Vertex>();
        for (Vertex v : vertexs) {
            unVisited.add(v);
        }
    }

    /*
     * 从未访问顶点集合中删除已找到最短路径的节点
     */
    private void pop() {
        unVisited.poll();
    }

    /*
     * 获取顶点到目标顶点的距离
     */
    private int getDistance(Vertex source, Vertex destination) {
        int sourceIndex = vertexs.indexOf(source);
        int destIndex = vertexs.indexOf(destination);
        return edges[sourceIndex][destIndex];
    }

    /*
     * 获取顶点所有(未访问的)邻居
     */
    private List<Vertex> getNeighbors(Vertex v) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        int position = vertexs.indexOf(v);
        Vertex neighbor = null;
        int distance;
        for (int i = 0; i < vertexs.size(); i++) {
            if (i == position) {
                //顶点本身，跳过
                continue;
            }
            distance = edges[position][i];    //到所有顶点的距离
            if (distance < Integer.MAX_VALUE) {
                //是邻居(有路径可达)
                neighbor = getVertex(i);
                if (!neighbor.isMarked()) {
                    //如果邻居没有访问过，则加入list;
                    neighbors.add(neighbor);
                }
            }
        }
        return neighbors;
    }

    /*
     * 根据顶点位置获取顶点
     */
    private Vertex getVertex(int index) {
        return vertexs.get(index);
    }

    /*
     * 打印图
     */
    public void printGraph() {
        int verNums = vertexs.size();
        for (int row = 0; row < verNums; row++) {
            for (int col = 0; col < verNums; col++) {
                if(Integer.MAX_VALUE == edges[row][col]){
                    System.out.print("X");
                    System.out.print(" ");
                    continue;
                }
                System.out.print(edges[row][col]);
                System.out.print(" ");
            }
            System.out.println();
        }
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
