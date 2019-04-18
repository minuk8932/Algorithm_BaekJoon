package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10971번: 외판원 순회 2
 *
 *	@see https://www.acmicpc.net/problem/10971/
 *
 */
public class Boj10971 {
	private static int[][] path;
	private static int[] tmp;
	private static boolean[] visit;
	
	private static int index, size = 1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			size *= (i + 1);
			
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		path = new int[size][N + 1];
		tmp = new int[N + 1];
		
		for(int start = 0; start < N; start++) {
			visit = new boolean[N];
			
			backTracking(N, 0, start);				// 백트래킹을 이용한 순회 가능 경우의 수를 모두 배열에 저장
		}
		
		System.out.println(getMinDistance(N, map));
	}
	
	private static void backTracking(int n, int depth, int current) {
		if(index == size) return;
		
		tmp[depth] = current;

		if(visit[current]) return;
		visit[current] = true;
		
		if(depth == n - 1) {
			for(int i = 0; i < n; i++) {
				path[index][i] = tmp[i];
			}
			
			path[index++][n] = tmp[0];
			return;
		}
		
		for(int next = 0; next < n; next++) {
			if(visit[next]) continue;
			
			backTracking(n, depth + 1, next);
			visit[next] = false;
		}
	}
	
	private static int getMinDistance(int n, int[][] arr) {
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < path.length; i++) {
			int cost = 0;
			boolean flag = true;
			
			for(int j = 1; j < path[i].length; j++) {
				if(arr[path[i][j - 1]][path[i][j]] == 0) {		// 경로가 없는 경우 값을 저장하지 않음
					flag = false;
					break;
				}
				
				cost += arr[path[i][j - 1]][path[i][j]];
			}
			
			if(flag) min = Math.min(cost, min);
		}
		
		return min;
	}
}
