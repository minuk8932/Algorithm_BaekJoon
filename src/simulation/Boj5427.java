package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5437 : 불
 *
 *	@see https://www.acmicpc.net/problem/5427/
 *
 */
public class Boj5427 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String CANNOT_ESCAPE = "IMPOSSIBLE";

	private static final char WAY = '.';
	private static final char BLOCK = '#';
	private static final char START = '@';
	private static final char FIRE = '*';

	private static final int[][] DIRECTIONS = {{ 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }};
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			int sRow = 0, sCol = 0, resRow = 0, resCol = 0;					// 시작위치와 안전히 탈출시 인덱스
			int[][] isVisited = new int[h][w];										// 방문체크 배열
			
			boolean esc = false;														// 탈출을 했는지 확인하는 진리변수
			boolean[][] fireSpot = new boolean[h][w];						// 불의 위치를 저장할 배열
			
			char[][] top = new char[h][w];
			
			for (int i = 0; i < h; i++) {
				String line = br.readLine();
				
				for(int j = 0; j < w; j++){
					top[i][j] = line.charAt(j);
					
					if(top[i][j] == START){
						sRow = i;
						sCol = j;
					}
					
					if(top[i][j] == FIRE){
						fireSpot[i][j] = true;
					}
				}
			}

			Queue<Point> q = new LinkedList<>();

			if (sRow == 0 || sRow == h - 1 || sCol == 0 || sCol == w - 1) {				// 시작위치가 인덱스 바깥쪽에 존재한다면
				resRow = sRow;
				resCol = sCol;
				isVisited[resRow][resCol] = 1;
				esc = true;																				// 1회만에 바로 탈출 가능
			} 
			else {
				int[][] fireVisited = new int[h][w];
				
				for(int i = 0; i < h; i++){
					for(int j = 0; j < w; j++){
						if(fireSpot[i][j]){
							q.offer(new Point(i, j));												// 불의위치를 BFS를 통해 확산
							fireVisited[i][j] = 1;
						}
					}
				}
				
				while (!q.isEmpty()) {
					Point current = q.poll();

					for (final int[] DIRECTION : DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];

						if (nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w) {
							if(top[nextRow][nextCol] != BLOCK && top[nextRow][nextCol] != START){				// 벽과 시작지점을 제외하고 불을 확산시킴
								if (fireVisited[nextRow][nextCol] == 0) {
									fireVisited[nextRow][nextCol] = fireVisited[current.row][current.col] + 1;
									
									q.offer(new Point(nextRow, nextCol));
								}
							}
						}
					}
				}
				
				q.offer(new Point(sRow, sCol));																					// 상근이의 탈출 경로를 추적
				isVisited[sRow][sCol] = 1;
				
				while (!q.isEmpty()) {
					Point current = q.poll();

					for (final int[] DIRECTION : DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];

						if (nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w) {
							if (isVisited[nextRow][nextCol] == 0 && top[nextRow][nextCol] == WAY &&			// 상근이가 방문하지 않았고, 지나갈 수 있는 길인 경우
									(fireVisited[nextRow][nextCol] > isVisited[current.row][current.col] + 1 || fireVisited[nextRow][nextCol] == 0)) {
								//	해당 위치로 불이 번질때 걸린 시간 > 상근이가 그 위치로 이동하기위해 걸린시간 이면, 또는 불이 아예 번지지 않은 경우 
								
								isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;			// 상근이를 이동시킴
								
								if(nextRow == 0 || nextRow == h - 1 || nextCol == 0 || nextCol == w - 1){			// 만약 상근이가 가장자리까지 안전하게 이동했다면
									if(!esc){
										resRow = nextRow;
										resCol = nextCol;
										esc = true;																							// 탈출 완료
									}
								}
								
								q.offer(new Point(nextRow, nextCol));														// 다음 이동할 인덱스를 큐에 저장
							}
						}
					}
				}
			}
			
			sb.append(esc ? isVisited[resRow][resCol] : CANNOT_ESCAPE).append(NEW_LINE);				// 상근이가 탈출한 경우 탈출할때까지의 시간을 버퍼에 담고,
		}																																		// 탈출 못했을 경우 IMPOSSIBLE을 버퍼에 저장
		
		br.close();

		System.out.println(sb.toString());																						// 결과값 한번에 출력
	}

	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
