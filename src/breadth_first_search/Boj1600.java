package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1600번: 말이 되고픈 원숭이
 *
 *	@see https://www.acmicpc.net/problem/1600/
 *
 */
public class Boj1600 {
	private static final int[][] K_DIRECTIONS = {{1, 2}, {2, 1}, {-1, 2}, {2, -1}, {1, -2}, {-2, 1}, {-1, -2}, {-2, -1}};
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 100_001;
	
	private static int[][][] isVisited = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		isVisited = new int[H][W][K + 1];
		
		int[][] map = new int[H][W];
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				Arrays.fill(isVisited[i][j], INF);	// 배열에 최댓값으로 채워줌
			}
		}
		
		System.out.println(bfs(H, W, K, map));		// 너비 우선 탐색 메소드를 통한 결과 값 출력
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Point{
		int row;
		int col;
		int jump;
		
		public Point(int row, int col, int jump) {
			this.row = row;
			this.col = col;
			this.jump = jump;
		}
	}
	
	/**
	 * 	너비 우선 탐색 메소드
	 *
	 */
	private static int bfs(int N, int M, int kMove, int[][] map) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0, 0));
		
		for(int i = 0; i < kMove; i++) {		// 출발 지점에서 가능한 모든 점프 경우에 1 저장
			isVisited[0][0][i] = 1;
		}
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(int j = 0; j < kMove; j++) {					// 말처럼 움직일 수 있는 횟수만큼 반복
				for(final int[] DIRECTION: DIRECTIONS) {		// 일반적인 움직임
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
						if(map[nextRow][nextCol] != 1) {
							// 벽이 아닌 경우에, 일반적인 움직임이므로 점프 횟수를 고정 시킨 방문 배열의 크기를 확인
							if(isVisited[nextRow][nextCol][current.jump] > isVisited[current.row][current.col][current.jump] + 1) {
								isVisited[nextRow][nextCol][current.jump] = isVisited[current.row][current.col][current.jump] + 1;
								
								// 각 경우마다 방문 배열을 최솟값으로 갱신하고, 목적지 도착한 경우 그때의 최소 이동횟수 반환
								if(nextRow == N - 1 && nextCol == M - 1) return isVisited[nextRow][nextCol][current.jump] - 1;
								
								q.offer(new Point(nextRow, nextCol, current.jump));
							}
						}
					}
				}
				
				if(current.jump < kMove) {						// 점프 횟수가 움직임 가능한 말 움직임 횟수보다 작을때까지
					for(final int[] DIRECTION: K_DIRECTIONS) {		// 말의 움직임
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
						int nextJump = current.jump + 1;			// 말로 움직인 경우로 점프횟수 +1
						
						if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
							if(map[nextRow][nextCol] != 1) {
								// 벽이 아닌 경우에 더해진 점프횟수에 해당하는 방문배열의 크기를 확인
								if(isVisited[nextRow][nextCol][nextJump] > isVisited[current.row][current.col][current.jump] + 1) {
									isVisited[nextRow][nextCol][nextJump] = isVisited[current.row][current.col][current.jump] + 1;
									
									// 각 경우마다 방문 배열을 최솟값으로 갱신하고, 목적지 도착한 경우 그때의 최소 이동횟수 반환
									if(nextRow == N - 1 && nextCol == M - 1) return isVisited[nextRow][nextCol][nextJump] - 1;
									
									q.offer(new Point(nextRow, nextCol, nextJump));
								}
							}
						}
					}
				}
			}
		}
		
		// 목적지에 도착하지 못한 경우
		return -1;
	}
}
