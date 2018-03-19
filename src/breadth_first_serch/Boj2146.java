package breadth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2146번 : 다리만들기
 *
 *	@see https://www.acmicpc.net/problem/2146
 *
 */
public class Boj2146 {
	private static final String SPACE = " ";
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static int N = 0;
	private static int[][] map = null;
	
	public static void main(String[] arsg) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			
			for(int j = 0; j < N; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		coverBFS();										// 각 섬마다 다른 숫자를 놓는 메소드
		
		int min = Integer.MAX_VALUE;		
		
		for(int row = 0; row < N ; row++){						// 실제 다리를 놓는 BFS
			for(int col = 0; col < N; col++){
				if(map[row][col] != 0){								// 육지에서 출발해야 하므로 육지만 검사
					int[][] isVisited = new int[N][N];
					
					int areaNum = map[row][col];
					isVisited[row][col] = 1;
					
					Queue<Point> q = new LinkedList<>();
					q.offer(new Point(row, col));
					
					while(!q.isEmpty()){
						Point current = q.poll();
						
						for(final int[] DIRECTION : DIRECTIONS){
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N){			// 범위 내에서
								if(isVisited[nextRow][nextCol] == 0){												// 방문하지 않았으며
									if(map[current.row][current.col] != 0){										// 현재 위치또한 육지인 경우 값을 1로 채워줌
										isVisited[nextRow][nextCol] = 1;
									}
									
									else{
										isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;	// 바다인 경우 다리를 놓으면서 이전 방문 횟수보다 1씩 증가시킴
										
										if(map[nextRow][nextCol] < areaNum){					// 만약 서로 다른 지역에 다리를 놓은 경우
											
											areaNum = map[nextRow][nextCol];						// 도달한 지역의 번호를 현재 맵으로 넣어주고
											
											min = Math.min(isVisited[nextRow][nextCol], min);	// 그때의 다리길이 최솟 값을 min에 담아줌
										}
									}
																
									q.offer(new Point(nextRow, nextCol));
								}
							}
						}
					}
				}
			}
		}
		
		
		
		System.out.println(min - 1);			// 처음 방문 횟수 1을 제외한 결과를 출력 
	}
	
	private static void coverBFS(){
		int area = -1;										// 구역마다 입력 할 각 구역 번호
		int[][] isVisited = new int[N][N];
		
		for(int row = 0; row < N; row++){
			for(int col = 0; col < N; col++){
				if(map[row][col] == 1 && isVisited[row][col] == 0){		// 바다 말고 방문안한 육지만,
					isVisited[row][col] = 1;
					
					map[row][col] = area;												// area 값을 담아줌
					
					Queue<Point> q = new LinkedList<>();
					q.offer(new Point(row, col));
					
					while(!q.isEmpty()){
						Point current = q.poll();
						
						for(final int[] DIRECTION : DIRECTIONS){
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N){				// 육지 내에서 area 값을 퍼트림 
								if(map[nextRow][nextCol] == 1 && isVisited[nextRow][nextCol] == 0){
									isVisited[nextRow][nextCol] = 1;
									
									map[nextRow][nextCol] = area;
									
									q.offer(new Point(nextRow, nextCol));
								}
							}
						}
					}
					
					area--;																		// area 값 감소
				}
			}
		}
		
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
}
