package Floyd_Warshall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6156번 Cow Contest
 *
 *	@see https://www.acimcpc.net/problem/6156/
 *
 */
public class Boj6156 {
	private static final int INF = 1_000_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] cows = new int[N + 1][N + 1];
		for(int i = 0; i < N + 1; i++) {
			Arrays.fill(cows[i], INF);
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			cows[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}
		
		System.out.println(floydWarshall(N, cows));
	}
	
	private static int floydWarshall(int n, int[][] arr) {
		int result = 0;
		
		for(int v = 1; v < n + 1; v++) {
			for(int s = 1; s < n + 1; s++) {
				for(int e = 1; e < n + 1; e++) {
					if(arr[s][e] > arr[s][v] + arr[v][e]) {		// 서열 정리
						arr[s][e] = arr[s][v] + arr[v][e];
					}
				}
			}
		}
		
		for(int i = 1; i < n + 1; i++) {
			int count = 0;
			
			count += rankDetermined(n, i, arr, true);
			count += rankDetermined(n, i, arr, false);
			if(count == n - 1) result++;			// 자신 빼고 서열이 정리되었다면 순서를 알 수 있음
		}
		
		
		return result;
	}
	
	private static int rankDetermined(int n, int x, int[][] arr, boolean check) {
		int value = 0;
		
		for(int y = 1; y < n + 1; y++) {			// 해당 소 x와 서열이 정리된 소의 마릿수를 확인
			if((check && arr[x][y] != INF) || (!check && arr[y][x] != INF)) value++;
		}
		
		return value;
	}
}
