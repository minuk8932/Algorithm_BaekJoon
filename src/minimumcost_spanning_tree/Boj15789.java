package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15789번: CTP 왕국은 한솔 왕국을 이길 수 있을까?
 *
 *	@see https://www.acmicpc.net/problem/15789/
 *
 */
public class Boj15789 {
	private static int[] parent;
	
	private static class Pair implements Comparable<Pair>{
		int count;
		int head;
		
		public Pair(int count, int head) {
			this.count = count;
			this.head = head;
		}

		@Override
		public int compareTo(Pair p) {
			return this.count > p.count ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		init(N);
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken()) - 1;
			int Y = Integer.parseInt(st.nextToken()) - 1;
			
			merge(X, Y);
		}
		
		st = new StringTokenizer(br.readLine());
		System.out.println(getPower(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
	}
	
	private static void init(int n) {
		parent = new int[n];
		for(int i = 0; i < n; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
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
	
	private static int getPower(int C, int H, int loop) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		int hParent = find(H);
		int cParent = find(C);

		int result = -parent[cParent];
		if(loop == 0) return result;
		
		for(int i = 0; i < parent.length; i++) {
			if(parent[i] > 0) continue;
			pq.offer(new Pair(-parent[i], i));			// 각 국가의 헤드 국가와 국가의 크기를 저장
		}
		
		while(loop > 0) {
			Pair current = pq.poll();
			
			if(current.head == hParent || current.head == cParent) continue;		// 동맹 불가
			result += current.count;
			loop--;
		}
		
		return result;
	}
}
