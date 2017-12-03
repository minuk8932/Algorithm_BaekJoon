package bandwidth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 *		Baek Joon : 2573번 빙산
 */

public class Boj2573 {
	private static final String SPACE = " ";

	private static final int CONCURRENT = 0;
	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static int[][] ice = new int[300][300];
	private static boolean[][] isVisited = null;
	
	/**
	 * main method
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int total = N * M;

		int max = 0;
		
		/**
		 * 		input
		 */
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), SPACE);
			for (int j = 0; j < M; j++) {
				ice[i][j] = Integer.parseInt(st.nextToken());

				if (i == 0 || i == N - 1 || j == 0 || j == M - 1) {
					ice[i][j] = 0;
				}
			}
		}

		int year = 0;
		int isDivide = 0, tmp = 0;
		
		/**
		 *  얼음이 어떻게 녹는지 탐색
		 *  얼음이 2동강 이상 날 경우 반복문 정지
		 *  
		 */
		LOOP: while (isDivide < 2) {
			isVisited = new boolean[N][M];
			
			int cnt = 0;
			
			isDivide = 0;
			year++;			
			
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < M; col++) {
					if(ice[row][col] == 0){
						cnt++;
					}
					
					if (ice[row][col] != 0 && !isVisited[row][col]) {
						isVisited[row][col] = true;
						
						isDivide++;
						
						Queue<Point> q = new LinkedList<>();
						q.offer(new Point(row, col));
						
						/**
						 *  현재 입력 받은 위치의 빙산이 녹는 경우
						 */
						meltedIce(row, col);
						

						while (!q.isEmpty()) {
							Point current = q.poll();

							for (final int[] DIRECTION : DIRECTIONS) {
								int nextRow = current.row + DIRECTION[ROW];
								int nextCol = current.col + DIRECTION[COL];

								if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
									if (ice[nextRow][nextCol] != 0 && !isVisited[nextRow][nextCol]) {
										isVisited[nextRow][nextCol] = true;
										
										q.offer(new Point(nextRow, nextCol));
										
										/**
										 * 	현재 녹은 빙산의 옆도 빙산일때 녹는 경우
										 */
										meltingIce(nextRow, nextCol);
									}
								}
							}
						}
					}
				}
			}
			/**
			 * 	만약 얼음이 2조각 이상 나지 않을 경우
			 */
			if(cnt == total){
				tmp = cnt;
				break LOOP;
			}
		}

		if(tmp == total){
			System.out.println(CONCURRENT);
		}
		else{
			System.out.println(year - 1);
		}
	}
	
	/**
	 * 탐색을 위한 좌표 클래스
	 * @author minchoba
	 *
	 */
	private static class Point {
		public int row;
		public int col;
		
		/**
		 * 
		 * @param row
		 * @param col
		 * 	행, 열에 따라 값을 받아서 처리
		 * 
		 */
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	/**
	 * 	처음 탐색하는 얼음을 녹이는 메소드
	 * @param x
	 * @param y
	 * 		해당 x,y의 배열 값이 얼음이었다면 -> 주변 배열을 검사해 얼음이면, 해당 얼음 주변의 0의 갯수에 따라 녹여줌
	 */
	private static void meltedIce(int x, int y){
		if (ice[x + 1][y] == 0) {
			if (ice[x][y] != 0)
				ice[x][y]--;
		}
		if (ice[x][y - 1] == 0) {
			if (ice[x][y] != 0)
				ice[x][y]--;
		}
		if (ice[x - 1][y] == 0) {
			if (ice[x][y] != 0)
				ice[x][y]--;
		}
		if (ice[x][y + 1] == 0) {
			if (ice[x][y] != 0)
				ice[x][y]--;
		}
	}
	
	/**
	 * 처음 탐색한 얼음 주변의 얼음들을 탐색해 녹이는 메소드
	 * @param x
	 * @param y
	 */
	private static void meltingIce(int x, int y){
		if (ice[x + 1][y] == 0 && !isVisited[x + 1][y]) {
			if (ice[x][y] != 0)
				ice[x][y]--;
		}
		if (ice[x][y - 1] == 0 && !isVisited[x][y - 1]) {
			if (ice[x][y] != 0)
				ice[x][y]--;
		}
		if (ice[x - 1][y] == 0 && !isVisited[x - 1][y]) {
			if (ice[x][y] != 0)
				ice[x][y]--;
		}
		if (ice[x][y + 1] == 0 && !isVisited[x][y + 1]) {
			if (ice[x][y] != 0)
				ice[x][y]--;
		}
	}
}
