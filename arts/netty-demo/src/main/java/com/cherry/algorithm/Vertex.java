package com.cherry.algorithm;

public class Vertex implements Comparable<Vertex>{

    /**
     * 节点名称(A,B,C,D)
     */
    private String name;
    
    /**
     * 最短路径长度
     */
    private int path;
    
    /**
     * 节点是否已经出列(是否已经处理完毕)
     */
    private boolean isMarked;
    
    public Vertex(String name){
        this.name = name;
        this.path = Integer.MAX_VALUE; //初始设置为无穷大
        this.setMarked(false);
    }
    
    public Vertex(String name, int path){
        this.name = name;
        this.path = path;
        this.setMarked(false);
    }
    
    @Override
    public int compareTo(Vertex o) {
        return o.path > path?-1:1;
    }

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final int getPath() {
		return path;
	}

	public final void setPath(int path) {
		this.path = path;
	}

	public final boolean isMarked() {
		return isMarked;
	}

	public final void setMarked(boolean isMarked) {
		this.isMarked = isMarked;
	}
    
    
}