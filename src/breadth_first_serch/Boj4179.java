package breadth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 4179번 : 불!
 *
 *	@see https://www.acmicpc.net/problem/4179
 *
 */
public class Boj4179 {
	private static final String SPACE = " ";
	private static final String NO_WAY = "IMPOSSIBLE";
	
	private static final char WAY = '.';
	private static final char START = 'J';
	private static final char FIRE = 'F';
	private static final char BLOCK = '#';
	
	private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{											// 유사문제 : @see Boj5427(불)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map  = new char[R][C];
		
		int sRow = 0, sCol = 0;
		boolean[][] fire = new boolean[R][C];
		
		
		for(int i = 0; i < R; i++){
			String line = br.readLine();
			
			for(int j = 0; j < C; j++){
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == START){				// 시작 인덱스 저장
					sRow = i;
					sCol = j;
				}
				
				if(map[i][j] == FIRE){					// 불이 존재하는 위치 저장
					fire[i][j] = true;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		int[][] isVisited = new int[R][C];
		int[][] fireVisited = new int[R][C];
		boolean escape = false;
		
		if(sRow == 0 || sRow == R - 1 || sCol == 0 || sCol == C - 1){	// 이미 외곽에서 존재할 경우
			System.out.println(1);
		}
		else{
			Queue<Point> q = new LinkedList<>();
			
			for(int i = 0; i < R; i++){
				for(int j = 0; j < C; j++){
					if(fire[i][j]){														// 불이 존재하는 경우
						q.offer(new Point(i, j));									// 큐에 담은 후
						
						fireVisited[i][j] = 1;											// 불이 존재하는 것으로 체크
					}
				}
			}
			
			while(!q.isEmpty()){
				Point current = q.poll();
				
				for(final int[] DIRECTION : DIRECTIONS){
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C){							// 불이 번지는 경로에따라 걸리는 각 시간을 배열에 담아줌
						if(fireVisited[nextRow][nextCol] == 0 && map[nextRow][nextCol] == WAY){
							fireVisited[nextRow][nextCol] = fireVisited[current.row][current.col] + 1;
							
							q.offer(new Point(nextRow, nextCol));
						}
					}
				}
			}
			
			q.offer(new Point(sRow, sCol));
			isVisited[sRow][sCol] = 1;
			
			while(!q.isEmpty()){
				Point current = q.poll();
				
				for(final int[] DIRECTION : DIRECTIONS){
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C){							// 불이 번진 경로에 따른 시간과 비교해 불이 아예 안번졌거나, 지훈이가 먼저 지날 수 있는 경우
						if(isVisited[nextRow][nextCol] == 0 && map[nextRow][nextCol] == WAY && 
								(fireVisited[nextRow][nextCol] > isVisited[current.row][current.col] + 1 || fireVisited[nextRow][nextCol] == 0)){
							isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;			// 지훈이의 이동경로에 따른 시간을 저장
							
							if(!escape){
								if(nextRow == 0 || nextRow == R - 1 || nextCol == 0 || nextCol == C - 1){
									escape = true;																					// 외곽에 도달 한 경우 탈출했음을 표시하고
									sb.append(isVisited[nextRow][nextCol]);												// 그때 걸린 시간을 출력 버퍼에 담아줌
								}
							}
							
							q.offer(new Point(nextRow, nextCol));
						}
					}
				}
			}
			System.out.println(escape ? sb.toString() : NO_WAY);													// 만약 탈출을 못했다면 IMPOSSIBLE을 탈출했다면 그때의 시간을 출력
		}
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *	정점 이너 클래스
	 */
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
}
