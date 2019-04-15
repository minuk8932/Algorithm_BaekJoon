package heap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6160번: Election Time
 *
 *	@see https://www.acmicpc.net/problem/6160/
 *
 */
public class Boj6160 {
	
	private static class Pair implements Comparable<Pair>{
		int idx;
		int cnt;
		
		public Pair(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Pair p) {
			return this.cnt > p.cnt ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] round1 = new int[N];
		int[] round2 = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			round1[i] = Integer.parseInt(st.nextToken());
			round2[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getTop(N, K, round1, round2));
	}
	
	private static int getTop(int n, int k, int[] arr1, int[] arr2) {
		PriorityQueue<Pair> elect = new PriorityQueue<>();
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		
		for(int i = 0; i < n; i++) {
			elect.offer(new Pair(i, arr1[i]));
		}
		
		while(k-- > 0) {
			Pair current = elect.poll();		// 1라운드 결과 k명을
			pq.offer(new Pair(current.idx, arr2[current.idx]));		// 2라운드로 진출
		}
		
		return pq.poll().idx + 1;		// 최종 1위
	}
}
