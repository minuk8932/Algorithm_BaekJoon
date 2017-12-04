package breadth_first_serch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 	약식
 * 	1 1 0 0
 * 	0 0 0 1			=> 열마다 갈 수 있는지? 방문했는지 일일이 검사해보면서 돌아간다.
 * 	1 1 0 0
 * 	1 1 0 1
 * 
 */

public class Boj2667 {
	private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0,-1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for(int row = 0; row < N; row++){
			String line = br.readLine();
			
			for(int col = 0; col < N; col++){
				map[row][col] = Character.getNumericValue(line.charAt(col));
			}
		}
		
		br.close();
		
		boolean[][] isVisited = new boolean[N][N];
		
		int areaCnt = 0;
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int row = 0; row < N; row++){			
			for(int col = 0; col < N; col++){
				if(map[row][col] == 1 && !isVisited[row][col]){
					isVisited[row][col] = true;
					
					Queue<Point> queue = new LinkedList<>();	
					queue.offer(new Point(row, col));
					
					areaCnt++;
					int nodeCnt = 1;
					
					while(!queue.isEmpty()){
						Point current = queue.poll();
						
						for(final int[] DIRECTION : DIRECTIONS){
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N){
								if(map[nextRow][nextCol] == 1 && !isVisited[nextRow][nextCol]){
									isVisited[nextRow][nextCol] = true;
									nodeCnt++; // 탐색 할때마다 노드 수 증가 시킨다, 가구의 수 체크 
									queue.offer(new Point(nextRow, nextCol));
								}
							}
						}
					}
					list.add(nodeCnt);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(areaCnt).append(NEW_LINE);
		
		Collections.sort(list);
		
		for(int nodeCnt : list) {
			sb.append(nodeCnt).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static class Point{
		public int row;
		public int col;
		
		public Point(int row, int col){
			this.col = col;
			this.row = row;
		}
	}

}
