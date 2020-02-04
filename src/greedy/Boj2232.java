package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 
 * 	@author exponential-e
 *	백준 2322번: 지뢰
 *
 *	@see https://www.acmicpc.com/problem/2322/
 *
 */
public class Boj2232 {
	private static PriorityQueue<Mine> pq = new PriorityQueue<>();
	private static final String NEW_LINE = "\n";
	
	private static class Mine implements Comparable<Mine>{
		int idx;
		int val;
		
		public Mine(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}

		@Override
		public int compareTo(Mine m) {
			return this.val > m.val ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] mine = new int[N];
		for(int i = 0; i < N; i++) {
			mine[i] = Integer.parseInt(br.readLine());
			pq.offer(new Mine(i, mine[i]));
		}
		
		System.out.println(boom(N, mine));
	}
	
	private static String boom(int n, int[] arr) {
		ArrayList<Integer> list = new ArrayList<>();
		boolean[] visit = new boolean[n];
		
		while(!pq.isEmpty()) {
			Mine current = pq.poll();
			
			if(visit[current.idx]) continue;				// boom the biggest
			visit[current.idx] = true;
			
			list.add(current.idx + 1);
			for(int i = current.idx + 1; i < n; i++) {		// chaining ++
				if(arr[i] >= arr[i - 1]) break;
				visit[i] = true;
			}
			
			for(int i = current.idx - 1; i >= 0; i--) {		// chaining --
				if(arr[i] >= arr[i + 1]) break;
				visit[i] = true;
			}
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		for(int i: list)
			sb.append(i).append(NEW_LINE);
	
		return sb.toString();
	}
}
