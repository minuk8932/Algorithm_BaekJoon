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
 *	백준 14923번: 미로탈출
 *
 *	@see https://www.acmicpc.net/problem/14923/
 *
 */
public class Boj14923 {
	private static final String SPACE = " ";
	private static final int WAY = 0;
	private static final int BLOCK = 1;
	
	private static final int INF = 1_000_001;
	private static final int NO_WAY = -1;
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	private static int[][][] isVisited = null;
	private static int[][] map = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
		
		st = new StringTokenizer(br.readLine());
		Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
		
		map = new int[N + 1][M + 1];
		for(int i = 1; i < N + 1; i++){
			st = new StringTokenizer(br.readLine());
					
			for(int j = 1; j < M + 1; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		isVisited = new int[N + 1][M + 1][2];
		for(int i = 1 ; i < N + 1; i++){
			for(int j = 1; j < M + 1; j++){
				Arrays.fill(isVisited[i][j], INF);
			}
		}
		
		breakingBfs(N, M, start, end);	// 너비 우선 탐색을 통한 목적지까지 걸린 비용을 구함
		
		// 벽을 부수고 이동한 거리와 부수지 않고 이동한 거리 중 최솟값을 구함, 두 경우 모두 도달 못할 경우 -1
		System.out.println(isVisited[end.row][end.col][1] == INF && isVisited[end.row][end.col][0] == INF ?
				NO_WAY : Math.min(isVisited[end.row][end.col][1], isVisited[end.row][end.col][0]) - 1);
	}

	/**
	 * 정점 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Point{
		int row;
		int col;
		int isBroken;
		
		public Point(int row, int col, int isBroken){
			this.row = row;
			this.col = col;
			this.isBroken = isBroken;
		}
	}
	
	/**
	 * 너비 우선 탐색 메소드
	 * 
	 */
	private static void breakingBfs(int N, int M, Point s, Point e){
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(s.row, s.col, s.isBroken));
		isVisited[s.row][s.col][s.isBroken] = 1;
		
		while(!q.isEmpty()){
			Point current = q.poll();
			
			for(final int[] DIRECTION : DIRECTIONS){
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				int isBreaking = current.isBroken;
				
				if(nextRow > 0 && nextRow < N + 1 && nextCol > 0 && nextCol < M + 1){
					// 벽을 부수지 않고 이동하는 경우, 현재 방문 배열값이 다음 값보다 작으면 0번 배열칸에 저장
					if(map[nextRow][nextCol] == WAY && isVisited[nextRow][nextCol][isBreaking] > isVisited[current.row][current.col][isBreaking] + 1){	
						isVisited[nextRow][nextCol][isBreaking] = isVisited[current.row][current.col][isBreaking] + 1;
						
						if(e.row == nextRow && e.col == nextCol) return;
						
						q.offer(new Point(nextRow, nextCol, isBreaking));
					}
					
					// 벽을 부수고 이동하는 경우, 현재 방문 배열값이 다음 값보다 작으면 1번 배열칸에 저장
					else if(map[nextRow][nextCol] == BLOCK && isBreaking == 0 && isVisited[nextRow][nextCol][isBreaking] > isVisited[current.row][current.col][isBreaking] + 1){
						isVisited[nextRow][nextCol][1] = isVisited[current.row][current.col][0] + 1;
						
						if(e.row == nextRow && e.col == nextCol) return;
						
						q.offer(new Point(nextRow, nextCol, 1));
					}
				}
			}
		}
	}
}