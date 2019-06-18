package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj6181 {
	private static int[] parent;
	
	private static class Pair implements Comparable<Pair>{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			int thisDist = Math.abs(this.x) + Math.abs(this.y);
			int pDist = Math.abs(p.x) + Math.abs(p.y);
			
			if (thisDist < pDist) return -1;
			else if(thisDist > pDist) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		init(N);
		
		Pair[] points = new Pair[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			points[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(getSet(N, D, points));
	}
	
	private static void init(int n) {
		parent = new int[n];
		
		for(int i = 0; i < n; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		return parent[x] = find(parent[x]);
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}
	
	private static StringBuilder getSet(int n, int d, Pair[] p) {
		StringBuilder sb = new StringBuilder();
		Arrays.sort(p);
		
		for(int i = 0; i < n; i++) {						// 정렬로는 안됨 모든 점 대상으로...?			
			for(int j = 0; j < n; j++) {
				if(getManhattanDistance(p[i], p[j], d)) {
					merge(i, j);
				}
			}
		}
		
		int max = 0;
		int count = 0;
		
		for(int i = 0; i < n; i++) {
			if(parent[i] < 0) {
				count++;
				max = Math.max(-parent[i], max);
			}
		}
		
		return sb.append(count).append(" ").append(max);
	}
	
	private static boolean getManhattanDistance(Pair p1, Pair p2, int distance) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y) <= distance ? true: false;
	}
}
