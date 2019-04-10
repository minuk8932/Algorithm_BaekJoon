package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17135번: 캐슬 디펜스
 *
 *	@see https://www.acmicpc.net/problem/17135/
 *
 */
public class Boj17135 {
	private static ArrayList<String> combination = new ArrayList<>();
	private static boolean[] visit;
	
	private static class Pair{
		int row;
		int col;
		int min;
		
		public Pair(int row, int col, int min) {
			this.row = row;
			this.col = col;
			this.min = min;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < M - 2; i++) {		// 최대 15C3의 포지션 결정
			visit = new boolean[M];
			backTracking(M, 1, i + "", i);
		}
		
		System.out.println(defense(N, M, D, map));
	}
	
	private static void backTracking(int m, int count, String set, int current) {
		if(visit[current]) return;
		
		if(count == 3) {
			combination.add(set);
			return;
		}
		
		for(int next = current + 1; next < m; next++) {
			if(visit[next]) continue;
			
			backTracking(m, count + 1, set + " " + next, next);
			visit[next] = false;
		}
	}
	
	private static int defense(int n, int m, int dist, int[][] field) {
		int max = 0;
		
		for(String str: combination) {
			StringTokenizer st = new StringTokenizer(str);			
			int[] achors = new int[3];
			
			for(int i = 0; i < 3; i++) {
				achors[i] = Integer.parseInt(st.nextToken()); 
			}
			
			int count = getCount(n, m, field, achors, dist);
			if(count > max) max = count;
		}
		
		return max;
	}
	
	private static int getCount(int n, int m, int[][] field, int[] achors, int dist) {
		int count = 0;
		int loop = n;
		
		int[][] tmp = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				tmp[i][j] = field[i][j];
			}
		}
		
		while(loop-- > 0) {
			Pair[] set = new Pair[3];
			
			for(int i = 0; i < 3; i++) {
				set[i] = new Pair(1000, 1000, Integer.MAX_VALUE);
			}
			
			Queue<Pair> q = new LinkedList<>();
			
			for(int row = 0; row < n; row++) {
				for(int col = 0; col < m; col++) {
					if(tmp[row][col] == 1) {
						q.offer(new Pair(row, col, 0));		// 적들의 위치
					}
				}
			}
			
			if(q.isEmpty()) break;
			
			while(!q.isEmpty()) {
				Pair current = q.poll();
				
				for(int idx = 0; idx < 3; idx++) {
					int diff = getManhattanDistance(n, achors[idx], current.row, current.col);		// 적과의 거리
					
					if(diff < set[idx].min) {
						set[idx] = new Pair(current.row, current.col, diff);
					}
					else if(diff == set[idx].min && current.col < set[idx].col) {		// 거리가 같으면 행값이 작은것 부터
						set[idx] = new Pair(current.row, current.col, set[idx].min);
					}
				}
			}
			
			for(Pair dead: set) {
				if(dead.row == 1000 || tmp[dead.row][dead.col] == 0) continue;
				if(dead.min <= dist) {				// 거리 내 사살 가능한 적만
					tmp[dead.row][dead.col] = 0;
					count++;
				}
			}
			
			for(int i = n - 1; i >= 1; i--) {		// 아래로 지도 푸쉬
				for(int j = 0; j < m; j++) {
					tmp[i][j] = tmp[i - 1][j];
				}
			}
			
			for(int i = 0; i < m; i++) {
				tmp[0][i] = 0;
			}
		}
		
		return count;
	}
	
	private static int getManhattanDistance(int aRow, int aCol, int row, int col) {
		return Math.abs(aRow - row) + Math.abs(aCol - col);
	}
}
