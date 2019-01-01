package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2098번: 외판원 순회
 *
 *	@see https://www.acmicpc.net/problem/2098/
 *
 */
public class Boj2098 {
	private static int[][] dp;
	private static final int INF = 1_000_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][N];
		dp = new int[N][1 << N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Arrays.fill(dp[i], -1);
			
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(getMinDistance(N, 0, 1, arr));
	}
	
	private static int getMinDistance(int n, int current, int isVisited, int[][] arr) {
		if(isVisited == (1 << n) - 1) return arr[current][0] != 0 ? arr[current][0]: INF;	// 이미 방문한 위치
		if(dp[current][isVisited] >= 0) return dp[current][isVisited];		// 최솟값으로 갱신된 길
		
		int min = INF;
		
		for(int next = 0; next < n; next++) {
			if((isVisited & (1 << next)) == 0 && arr[current][next] > 0) {		// 경로가 존재하고, 방문하지 않은 길인 경우
				int distance = getMinDistance(n, next, (isVisited | (1 << next)), arr) + arr[current][next];		// 거리 계산
				min = Math.min(distance, min);		// 최단경로
			}
		}
		
		return dp[current][isVisited] = min;
	}
}
