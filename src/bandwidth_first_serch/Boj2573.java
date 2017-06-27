package bandwidth_first_serch;

// 빙산의 일각일 뿐..

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2573 {
	private static final String SPACE = " ";

	private static final int[][] DIRECTIONS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final int NON_SEPARATE = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[][] ice = new int[M][N];

		int year = 0, icebergCnt = 0;
		int max = 0;

		for (int row = 0; row < M; row++) {
			st = new StringTokenizer(br.readLine(), SPACE);
			for (int col = 0; col < N; col++) {
				ice[row][col] = Integer.parseInt(st.nextToken());
				
				max = Math.max(ice[row][col], max);
			}
		}
		br.close();
		
		while (icebergCnt < 2) {
			boolean[][] isVisited = new boolean[M][N];
			icebergCnt = 0;
			
			for (int row = 0; row < M; row++) {
				for (int col = 0; col < N; col++) {
					if (ice[row][col] != 0 && !isVisited[row][col]) {
			
						isVisited[row][col] = true;
						ice[row][col] -= 1;					
						
						icebergCnt++;
						
						
						Queue<Point> queue = new LinkedList<>();
						queue.offer(new Point(row, col));

						while (!queue.isEmpty()) {
							Point current = queue.poll();
							int sea = 0;
							
							for (final int[] DIRECTION : DIRECTIONS) {
								int nextRow = current.row + DIRECTION[ROW];
								int nextCol = current.col + DIRECTION[COL];

								if (nextRow >= 0 && nextRow < M && nextCol >= 0 && nextCol < N) {
									if (ice[nextRow][nextCol] != 0 && !isVisited[nextRow][nextCol]) {
										isVisited[nextRow][nextCol] = true;
										
										ice[nextRow][nextCol] -= 1;
										
										queue.offer(new Point(nextRow, nextCol));
										
									}
									
									if(ice[nextRow][nextCol] == 0){
										sea++;
									}
									
								}
							}
						}
						
					}
				}
				
			}
			
			if(year == max){
				break;
			}
			
			year++;	// 녹는 해 체크.
			
		}
		
		
		if(year == max){
			System.out.println(NON_SEPARATE);
		}
		else{
			System.out.println(year - 1);
		}
		
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
