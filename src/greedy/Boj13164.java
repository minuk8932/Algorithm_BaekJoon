package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13164번: 행복 유치원
 *
 *	@see https://www.acmicpc.net/problem/13164/
 *
 */
public class Boj13164 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] kinder = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			kinder[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(makeSet(N, K, kinder));
	}
	
	private static long makeSet(int n, int k, int[] arr) {
		long result = 0;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 1; i < n; i++) {						// 각 키차이를 구해서
			pq.offer(arr[i] - arr[i - 1]);
		}
		
		int loop = n - k;
		
		while(loop-- > 0) {				// 적은 차 부터 앞에서 더함
			result += pq.poll();
		}
		
		return result;
	}
}
