package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1726번 : 로봇
 *
 *	@see https://www.acmicpc.net/problem/1726
 *
 */
public class Boj1726 {
	private static final String SPACE = " ";

	private static final int[] DIRECTION_ROW = {0, 0, 0, 1, -1};				// 동 서 남 북 순서대로 배열에 담음
	private static final int[] DIRECTION_COL = {0, 1, -1, 0, 0};

	private static final int EAST = 1;
	private static final int WEST = 2;
	private static final int SOUTH = 3;
	private static final int NORTH = 4;
	
	private static int N = 0;
	private static int M = 0;
	
	private static int[][] map = null;
	private static boolean[][][] isVisited = null;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M + 1][N + 1];
		isVisited = new boolean[M + 1][N + 1][5];

		for (int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine(), SPACE);

			for (int j = 1; j < N + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), SPACE);
		int sRow = Integer.parseInt(st.nextToken());
		int sCol = Integer.parseInt(st.nextToken());
		int sDir = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), SPACE);
		int fRow = Integer.parseInt(st.nextToken());
		int fCol = Integer.parseInt(st.nextToken());
		int fDir = Integer.parseInt(st.nextToken());
		
		br.close();

		bfs(sRow, sCol, sDir, fRow, fCol, fDir);				// BFS 알고리즘
	}

	private static class Point {
		int row;
		int col;
		int dir;
		int cnt;

		public Point(int row, int col, int dir, int cnt) {
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.cnt = cnt;
		}
	}
	
	/**
	 * 
	 * @param sRow : 시작 행
	 * @param sCol : 시작 열
	 * @param sDir : 시작 방향
	 * @param fRow : 끝 열
	 * @param fCol : 끝 행
	 * @param fDir : 끝 방향
	 * 
	 */
	private static void bfs(int sRow, int sCol, int sDir, int fRow, int fCol, int fDir){
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(sRow, sCol, sDir, 0));

		isVisited[sRow][sCol][sDir] = true;			// 큐에 담은 위치와 방향을 방문한것으로 표기

		while (!q.isEmpty()) {
			Point current = q.poll();
			
			if(current.row == fRow && current.col == fCol && current.dir == fDir){		// 목적지에 도달한 경우 그때의 명령 횟수를 출력하고 알고리즘 종료 (결과값 출력)
				System.out.println(current.cnt);
				return;
			}

			for (int cam = 1; cam < 4; cam++) {															// 직진 명령
				int nextRow = current.row + DIRECTION_ROW[current.dir] * cam;			// 다음 열로 진행을 큐에 담겨있던 방향을 이용해 접근
				int nextCol = current.col + DIRECTION_COL[current.dir] * cam;
				
				if(nextRow < 1 || nextRow > M || nextCol < 1 || nextCol > N || map[nextRow][nextCol] == 1){		// 더 이상 갈 곳이 없다면 종료
					break;
				}
				
				if(isVisited[nextRow][nextCol][current.dir]){										// 이미 방문 했던 곳이면 다음 것 부터 다시 실행
					continue;
				}
				
				isVisited[nextRow][nextCol][current.dir] = true;									// 방문을 안했다면 방문한것으로 표기
				
				q.offer(new Point(nextRow, nextCol, current.dir, current.cnt+1));		// 다음 위치와 방향, 명령이행+1 을 큐에 담음
			}
			
			for(int cam = 1; cam < 5; cam++){															// 방향 전환 명령
				if(cam == current.dir || cam == chDir(current.dir)){								// 같은 방향으로 움직였거나, 반대방향으로 움직이려는 경우 변경 (위에서 같은방향 처리는 이미 했음)
					continue;
				}
				
				if(isVisited[current.row][current.col][cam]){										// 방문했던 위치와 방향이면 다음 것 부터 진행
					continue;
				}
				
				isVisited[current.row][current.col][cam] = true;									// 방문하지 않았다면, 방문한 것으로 표기
				
				q.offer(new Point(current.row, current.col, cam, current.cnt+1));		// 큐에 다음 방향 전환명령을 위해 값을 담고, 명령이행 +1 도 함께 담아줌
			}

		}
	}
	
	private static int chDir(int d){	// 입력된 방향과 반대로 값을 담아줌
		if(d == EAST){
			return WEST;
		}
		
		else if(d == WEST){
			return EAST;
		}
		
		else if(d == NORTH){
			return SOUTH;
		}
		
		else{
			return NORTH;
		}
	}
}
