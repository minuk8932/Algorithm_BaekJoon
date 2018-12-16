package minimumcost_spanning_tree;

public class Edge implements Comparable<Edge>{
	public int start;
	public int end;
	public int cost;
	
	public Edge(int start, int end, int cost){
		this.start = start;
		this.end = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge e) {
		return cost < e.cost ? -1 : 1;
	}
}
