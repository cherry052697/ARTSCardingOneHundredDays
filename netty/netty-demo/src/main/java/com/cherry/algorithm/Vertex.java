package com.cherry.algorithm;

import java.util.List;

public class Vertex {
	 @SuppressWarnings("rawtypes")
	List adj;
	 boolean known;
	 DistType dist;
	 Vertex path;
	@SuppressWarnings("rawtypes")
	public final List getAdj() {
		return adj;
	}
	@SuppressWarnings("rawtypes")
	public final void setAdj(List adj) {
		this.adj = adj;
	}
	public final boolean isKnown() {
		return known;
	}
	public final void setKnown(boolean known) {
		this.known = known;
	}
	public final DistType getDist() {
		return dist;
	}
	public final void setDist(DistType dist) {
		this.dist = dist;
	}
	public final Vertex getPath() {
		return path;
	}
	public final void setPath(Vertex path) {
		this.path = path;
	}
	 
}
