package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 8980번: 택배
 *
 *	@see https://www.acmicpc.net/problem/8980/
 *
 */
public class Boj8980 {
	private static class Package implements Comparable<Package> {
		int from;
		int to;
		int capacity;
		
		public Package(int from, int to, int capacity) {
			this.from = from;
			this.to = to;
			this.capacity = capacity;
		}
		
		@Override
		public int compareTo(Package p) {
			if(this.to < p.to) return -1;
			else if(this.to > p.to) return 1;
			else return this.from < p.from ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		
		PriorityQueue<Package> pack = new PriorityQueue<>();
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			pack.offer(new Package(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
		}
		
		System.out.println(delivery(N, C, pack));
	}
	
	private static int delivery(int n, int cap, PriorityQueue<Package> pq) {
		int total = 0;
		int[] sum = new int[n];
		
		while(!pq.isEmpty()) {
			Package current = pq.poll();		// 배송 거리가 가장 짧은 택배부터
			int max = 0;
			
			for(int village = current.from; village < current.to; village++) {		// 범위내 마을 중 가장 많은 배달량
				max = Math.max(max, sum[village]);
			}
			
			int spare = Math.min(current.capacity, cap - max);						// 범위내 배달량을 제외한 나머지와 현재 용량 중 최소
			total += spare;															// 즉, 조건 내 최대 배달 가능량
			
			for(int village = current.from; village < current.to; village++) {		// 배달한 크기를 채워넣음
				sum[village] += spare;
			}
		}
		
		return total;
	}
}
