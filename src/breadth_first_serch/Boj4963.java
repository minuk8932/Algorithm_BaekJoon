package breadth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 * 	백준 4963번 : 섬의 개수
 * 
 * 	@see https://www.acmicpc.net/problem/4963/
 * 
 */
public class Boj4963 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			if(w == 0 && h == 0){						// 마지막 입력이 0 0 일때까지 무한 반복
				break;
			}
			
			int[][] map = new int[h][w];
			for(int i = 0; i < h; i++){
				st = new StringTokenizer(br.readLine(), SPACE);
				for(int j = 0; j < w; j++){
					
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			boolean[][] isVisited = new boolean[h][w];
			int isLand = 0;
			
			for(int row = 0; row < h; row++){
				for(int col = 0; col < w; col++){
					if(!isVisited[row][col] && map[row][col] == 1){	// 방문하지 않았고, 그곳이 땅일 경우
						isVisited[row][col] = true;
						
						isLand++;													// 섬 1개 추가
						
						Queue<Point> q = new LinkedList<>();
						q.offer(new Point(row, col));
						
						while(!q.isEmpty()){
							Point current = q.poll();
							
							for(final int[] DIRECTION : DIRECTIONS){				// 동 서 남 북 남서 남북 동북 동남 - 8 방향으로 탐색 실행
								int nextRow = current.row + DIRECTION[ROW];
								int nextCol = current.col + DIRECTION[COL];
								
								if(nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w){
									if(!isVisited[nextRow][nextCol] && map[nextRow][nextCol] == 1){	// 다음 지역도 당연히 방문하지 않았으며, 땅일 경우
										isVisited[nextRow][nextCol] = true;
										
										q.offer(new Point(nextRow, nextCol));									// 다음 구역을 큐에 담고 반복
									}
								}
							}
						}
					}
				}
			}
			
			sb.append(isLand).append(NEW_LINE);																	// 각 테이스케이스 별로 섬의 갯수를 버퍼에 담고
		}
		
		System.out.println(sb.toString());																			// 결과값 한번에 출력
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
