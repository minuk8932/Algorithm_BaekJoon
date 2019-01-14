package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2961번: 도영이가 만든 맛있는 음식
 *
 *	@see https://www.acmicpc.net/problem/2961/
 *
 */
public class Boj2961 {
	private static boolean[] isUsed;
	private static long min = Long.MAX_VALUE;
	
	private static final int INF = 1_000_000_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[][] SB = new long[N][2];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			SB[i][0] = Integer.parseInt(st.nextToken());
			SB[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			isUsed = new boolean[N];
			
			backTracking(N, i, SB, 1, 0);
		}
		
		System.out.println(min);
	}
	
	private static void backTracking(int n, int idx, long[][] arr, long sour, long bitter) {
		if(n == idx) return;
		
		if(isUsed[idx]) return;
		isUsed[idx] = true;
		
		if(sour * arr[idx][0] >= INF || bitter + arr[idx][1] >= INF) return;	// 맛의 값이 10억 초과시
		
		sour *= arr[idx][0];
		bitter += arr[idx][1];
		
		min = Math.min(min, Math.abs(sour - bitter));		// 최소 차이
		
		for(int next = idx + 1; next < n; next++) {
			if(isUsed[next]) continue;
			if(next == idx) continue;
			
			backTracking(n, next, arr, sour, bitter);		// 재귀적으로 맛의 값 계산
			isUsed[next] = false;							// 백 트래킹
		}
	}
}
