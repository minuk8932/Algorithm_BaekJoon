package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2178번 : 미로탐색
 *
 *	@see https://www.acmicpc.net/problem/2178
 *
 */
public class Boj2178 {
	public static final String SPACE = " ";
	private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0,-1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];			// 미로 사이즈 설정
		
		for(int row = 0; row < N; row++){
			String line = br.readLine(); 
			
			for(int col = 0; col < M; col++){
				map[row][col] = Character.getNumericValue(line.charAt(col));
			}
		}
		
		br.close();
		
		int[][] isVisited = new int[N][M];
		isVisited[0][0] = 1;
		
		Queue<Point> queue = new LinkedList<>();	// 시작점 부터 BFS 실행
		queue.offer(new Point(0, 0));
		
		while(!queue.isEmpty()){
			Point current = queue.poll();
			
			for(final int[] DIRECTION : DIRECTIONS){
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M){			// 맵의 범위 내에서
					if(map[nextRow][nextCol] == 1){ 														// 갈 수 있는 길인지?
						if(isVisited[nextRow][nextCol] == 0){											// 방문했던 곳인가?
							isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;	 // isVisited 내에 depth를 담아줌
							
							queue.offer(new Point(nextRow, nextCol));
						}
					}
				}
			}
		}
		
		System.out.println(isVisited[N-1][M-1]);	// 최종 지점에서의 깊이를 출력해줌
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *	정점 이너클래스
	 *
	 */
	private static class Point{
		public int row;
		public int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}

}
