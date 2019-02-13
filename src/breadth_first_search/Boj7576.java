package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 7576번 : 토마토
 *
 *	@see https://www.acmicpc.net/problem/7576
 *
 */
public class Boj7576 {
	public static final String SPACE = " ";
	private static final int[] STATE = {-1, 0, 1};
	
	private static final int[][] DIRECTIONS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		int[][] isVisited = new int[N][M];
		
		int raredCnt = 0;
		Queue<Point> queue = new LinkedList<>();
		
		for(int row = 0; row < N; row++){
			st = new StringTokenizer(br.readLine(), SPACE);
			
			for(int col = 0; col < M; col++){
				int tomato = Integer.parseInt(st.nextToken());
				
				if(tomato == STATE[0]) {
					map[row][col] = STATE[0];
				}
				else if(tomato == STATE[1]) {
					raredCnt++;
				}
				else {
					map[row][col] = STATE[2];
					queue.offer(new Point(row, col));
					isVisited[row][col] = 1;
				}
			}
		}
		
		br.close();
		System.out.println(bfs(N, M, queue, map, isVisited, raredCnt));
	}
	
	private static int bfs(int n, int m, Queue<Point> q, int[][] map, int[][] visit, int count) {
		int maxDays = 1;
		
		while(!q.isEmpty()){							// 토마토 위치별로 BFS 알고리즘 실행
			Point current = q.poll();
			
			for(final int[] DIRECTION : DIRECTIONS){
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m){
					if(map[nextRow][nextCol] == STATE[1] && visit[nextRow][nextCol] == 0){
						
						q.offer(new Point(nextRow, nextCol));
						map[nextRow][nextCol] = STATE[2];														// 익은것
						visit[nextRow][nextCol] = visit[current.row][current.col] + 1; 
						maxDays = Math.max(maxDays, visit[nextRow][nextCol]);			// 최대 일수
						count--;
					}
				}
			}
		}
		
		if(count == 0)	return maxDays -1;	// 다 익었으면 걸린 익는데 날짜를 출력 
		else return STATE[0];				// 안익은게 있다면 -1 출력
	}
}
