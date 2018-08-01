package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5558번: チーズ
 *
 *	@see https://www.acmicpc.net/problem/5558/
 *
 */
public class Boj5558 {
	private static final int[][] DIRECTIONS = {{1 ,0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static char[][] map = null;
	private static int[][][] isVisited = null;
	
	private static final char START = 'S';
	private static final char BLOCK = 'X';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		Point start = new Point(0, 0, 0);
		
		map = new char[H][W];
		isVisited = new int[H][W][N + 2];
		
		for(int i = 0; i < H; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < W; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == START) start = new Point(i, j, 1);	// 시작 정점 저장
			}
		}
		
		System.out.println(bfs(H, W, N, start));		// 너비 우선 탐색을 통한 값 출력
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Point{
		int row;
		int col;
		int des;
		
		public Point(int row, int col, int des) {
			this.row = row;
			this.col = col;
			this.des = des;
		}
	}
	
	/**
	 * 너비 우선 탐색 메서드
	 * 
	 */
	private static int bfs(int N, int M, int K, Point s) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(s.row, s.col, s.des));
		
		isVisited[s.row][s.col][s.des] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				int nextDes = current.des;
				
				if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
					if(isVisited[nextRow][nextCol][nextDes] == 0 && map[nextRow][nextCol] != BLOCK) {
						if(nextDes == K + 1) return isVisited[current.row][current.col][nextDes] - 1;	// 최종 목적지 도착시 방문배열 값 - 1을 반환
						
						isVisited[nextRow][nextCol][nextDes] = isVisited[current.row][current.col][nextDes] + 1;
						
						// 현재 목적지에 도달시, 다음 목적지 값 +1과 동시에, 배열 차원을 올리며, 현재 방문 배열+1 값을 저장
						if((map[nextRow][nextCol] - '0') == nextDes) isVisited[nextRow][nextCol][++nextDes] = isVisited[current.row][current.col][current.des] + 1;
						
						q.offer(new Point(nextRow, nextCol, nextDes));
					}
				}
			}
		}
		
		return -1;		// 도달하지 못할 경우 (필요없음)
	}
}
