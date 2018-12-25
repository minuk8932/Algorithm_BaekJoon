package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14890번: 경사로
 *
 *	@see https://www.acmicpc.net/problem/14890/
 *
 */
public class Boj14890 {
	private static boolean[] covered;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(roadCounts(N, L, map));
	}
	
	private static int roadCounts(int n, int l, int[][] arr) {
		int moving = 0;
		
		for(int row = 0; row < n; row++) {				// 행 검사
			int current = arr[row][0];
			covered = new boolean[n];
			boolean isPassed = false;
			
			for(int col = 1; col < n; col++) {
				int next = arr[row][col];
				if(Math.abs(current - next) > 1) break;
				
				if(current != next) {				
					if(current > next) {
						isPassed = rowBridge(arr, n, l, row, col, next, 1, 0, l, n - 1);		// 낮은 방향이동
					}
					else {
						isPassed = rowBridge(arr, n, l, row, col, current, -1, 1, l + 1, 0);	// 높은 방향 이동
					}
				}
				
				if(isPassed) break;			// 이동 불가
				if(col == n - 1) moving++;
				current = next;
			}
		}
		
		for(int col = 0; col < n; col++) {				// 열 검사
			int current = arr[0][col];
			covered = new boolean[n];
			boolean isPassed = false;
			
			for(int row = 1; row < n; row++) {
				int next = arr[row][col];
				if(Math.abs(current - next) > 1) break;
				
				if(current != next) {				
					if(current > next) {
						isPassed = colBridge(arr, n, l, row, col, next, 1, 0, l, n - 1);
					}
					else {
						isPassed = colBridge(arr, n, l, row, col, current, -1, 1, l + 1, 0);
					}
				}
				
				if(isPassed) break;
				if(row == n - 1) moving++;
				current = next;
			}
		}
		
		return moving;
	}
	
	private static boolean rowBridge(int[][] arr, int n, int l, int row, int col, int next, int diff, int start, int end, int limit) {
		int cnt = 0;
		limit = ((limit * diff) + limit) / 2;		// 지도 범위
		
		for(int i = start; i < end; i++) {
			int idx = col + (i * diff);
			
			if(idx * diff > limit) break;
			if(covered[idx]) break;				// 이미 경사로가 덮힌 경우
			if(next == arr[row][idx]) cnt++;	// 경사로를 덮을 수 있는 경우
		}
		
		if(l != cnt) {			// 경사로를 설치할 수 없음
			return true;
		}
		else {
			for(int i = start; i < end; i++) {		// 경사로 설치
				int idx = col + (i * diff);
				covered[idx] = true;
			}
			
			return false;
		}
	}
	
	private static boolean colBridge(int[][] arr, int n, int l, int row, int col, int next, int diff, int start, int end, int limit) {
		int cnt = 0;
		limit = ((limit * diff) + limit) / 2;
		
		for(int i = start; i < end; i++) {
			int idx = row + (i * diff);
			
			if(idx * diff > limit) break;
			if(covered[idx]) break;
			if(next == arr[idx][col]) cnt++;
		}
		
		if(l != cnt) {
			return true;
		}
		else {
			for(int i = start; i < end; i++) {
				int idx = row + (i * diff);
				covered[idx] = true;
			}
			
			return false;
		}
	}
}
