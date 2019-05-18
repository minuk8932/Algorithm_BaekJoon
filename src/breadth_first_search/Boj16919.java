package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16919번: 봄버맨 2
 *
 *	@see https://www.acmicpc.net/problem/16919/
 *
 */
public class Boj16919 {
	private static int[][] timer;
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final char EMPTY = '.';
	private static final char BOMB = 'O';
	
	private static final String NEW_LINE = "\n";
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][C];
		timer = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == BOMB) timer[i][j]++;
			}
		}
		
		bfs(R, C, N, map);
	}
	
	private static void bfs(int N, int M, int T, char[][] arr) {		
		if(T == 1) {
			System.out.println(getMapState(N, M, arr));
			return;
		}
		
		int count = 0;
		T -= 2;
		T %= 4;				// 반복 횟수 줄이기
		
		while(count <= T) {
			Queue<Point> q = new LinkedList<>();
			
			if(count % 2 == 0) {
				for(int row = 0; row < N; row++) {
					for(int col = 0; col < M; col++) {
						if(arr[row][col] == BOMB) timer[row][col]++;				// 폭탄 시간초++
						else arr[row][col] = BOMB;
					}
				}
			}
			else {
				for(int row = 0; row < N; row++) {
					for(int col = 0; col < M; col++) {
						if(arr[row][col] == BOMB) {
							timer[row][col]++;
							if(timer[row][col] % 3 == 0) q.offer(new Point(row, col));		// 빈공간 폭탄 설치
						}
					}
				}
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					arr[current.row][current.col] = EMPTY;
					timer[current.row][current.col] = 0;
							
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
								
						if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
						arr[nextRow][nextCol] = EMPTY;
						timer[nextRow][nextCol] = 0;
					}
				}
			}
			
			count++;
		}
		
		System.out.println(getMapState(N, M, arr));
	}
	
	private static StringBuilder getMapState(int N, int M, char[][] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {		// 최종 상태 출력
				sb.append(arr[row][col]);
			}
			sb.append(NEW_LINE);
		}
		
		return sb;
	}
}
