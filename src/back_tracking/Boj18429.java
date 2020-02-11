package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 18429번: 근손실
 *
 *	@see https://www.acmicpc.net/problem/18249/
 *
 */
public class Boj18429 {
	private static ArrayList<Integer> comb = new ArrayList<>();
	private static boolean[] visit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] day = new int[N];		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			day[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {
			visit = new boolean[N + 1];
			visit[i] = true;
			backTracking(N, i, i, 0);				// make permutation
		}
		
		System.out.println(muscleLoss(N, K, day));
	}
	
	private static int muscleLoss(int n, int k, int[] arr) {
		int count = 0;
		
		for(int seq: comb) {
			count += calculate(k, seq, arr);		// check muscle loss
		}
		
		return count;
	}
	
	private static void backTracking(int n, int val, int current, int count) {
		if(count == n - 1) {
			comb.add(val);
			return;
		}
		
		for(int next = 1; next <= n; next++) {
			if(visit[next]) continue;
			visit[next] = true;
			
			backTracking(n, val * 10 + next, next, count + 1);
			visit[next] = false;
		}
	}
	
	private static int calculate(int k, int s, int[] arr) {
		int sum = 0;
		
		while(s > 0) {
			int idx = (s % 10) - 1;
			sum += (arr[idx] - k);
			
			if(sum < 0) return 0;
			s /= 10;
		}

		return 1;
	}
}
