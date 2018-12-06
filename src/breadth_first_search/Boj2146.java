package breadth_first_search;
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
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static Queue<Point> q;
	
	public static void main(String[] arsg) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		br.close();
		
		map = coverBFS(N, map);				// 각 섬마다 다른 숫자를 놓는 메소드
		System.out.println(bfs(N, map)); 
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	private static int[][] coverBFS(int n, int[][] arr){
		int area = -1;
		boolean[][] isVisited = new boolean[n][n];
		
		for(int row = 0; row < n; row++){
			for(int col = 0; col < n; col++){
				if(arr[row][col] == 1 && !isVisited[row][col]){		// 바다 말고 방문안한 육지만,
					isVisited[row][col] = true;
					
					arr[row][col] = area;
					
					q = new LinkedList<>();
					q.offer(new Point(row, col));
					
					while(!q.isEmpty()){
						Point current = q.poll();
						
						for(final int[] DIRECTION : DIRECTIONS){
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n){				// 육지 내에서 area 값을 퍼트림 
								if(arr[nextRow][nextCol] == 1 && !isVisited[nextRow][nextCol]){
									isVisited[nextRow][nextCol] = true;
									
									arr[nextRow][nextCol] = area;
									
									q.offer(new Point(nextRow, nextCol));
								}
							}
						}
					}
					
					area--;
				}
			}
		}
		
		return arr;
	}
	
	private static int bfs(int n, int[][] arr) {
		int min = Integer.MAX_VALUE;		
		
		for(int row = 0; row < n ; row++){						// 실제 다리를 놓는 BFS
			for(int col = 0; col < n; col++){
				if(arr[row][col] != 0){
					int[][] isVisited = new int[n][n];
					
					int areaNum = arr[row][col];
					isVisited[row][col] = 1;
					
					q = new LinkedList<>();
					q.offer(new Point(row, col));
					
					while(!q.isEmpty()){
						Point current = q.poll();
						
						for(final int[] DIRECTION : DIRECTIONS){
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n){
								if(isVisited[nextRow][nextCol] == 0){												// 방문하지 않았으며
									if(arr[current.row][current.col] != 0){										// 현재 위치또한 육지인 경우 값을 1로 채워줌
										isVisited[nextRow][nextCol] = 1;
									}
									
									else{
										isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
										
										if(arr[nextRow][nextCol] < areaNum){					// 다리를 놓은 경우
											areaNum = arr[nextRow][nextCol];
											
											min = Math.min(isVisited[nextRow][nextCol], min);	// 다리 길이 최솟값
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
		
		return min - 1;
	}
}
