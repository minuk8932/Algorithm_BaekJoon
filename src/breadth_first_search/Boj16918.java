package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16918번: 봄버맨
 *
 *	@see https://www.acmicpc.net/problem/16918/
 *
 */
public class Boj16918 {
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
		if(T == 1) {		// 1초에 끝나는 경우
			System.out.println(getMapState(N, M, arr));
			return;
		}
		
		int count = 0;
		T -= 2;				// 규칙적으로 맵이 변경되므로 반복 횟수를 줄여줌
		T %= 4;
		
		while(count <= T) {
			Queue<Point> q = new LinkedList<>();
			
			if(count % 2 == 0) {
				for(int row = 0; row < N; row++) {			// 폭탄 위치엔 타이머 +1, 그 외 폭탄 설치
					for(int col = 0; col < M; col++) {
						if(arr[row][col] == BOMB) timer[row][col]++;
						else arr[row][col] = BOMB;
					}
				}
			}
			else {
				for(int row = 0; row < N; row++) {
					for(int col = 0; col < M; col++) {
						if(arr[row][col] == BOMB) {
							timer[row][col]++;
							if(timer[row][col] % 3 == 0) q.offer(new Point(row, col));		// 폭발 예정인 폭탄 위치 저장
						}
					}
				}
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					arr[current.row][current.col] = EMPTY;			// 폭파
					timer[current.row][current.col] = 0;
							
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
								
						if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
						arr[nextRow][nextCol] = EMPTY;
						timer[nextRow][nextCol] = 0;				// 인접 폭파
					}
				}
			}
			
			count++;
		}
		
		System.out.println(getMapState(N, M, arr));					// 맵 상태 출력
	}
	
	private static StringBuilder getMapState(int N, int M, char[][] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {
				sb.append(arr[row][col]);
			}
			sb.append(NEW_LINE);
		}
		
		return sb;
	}
}
