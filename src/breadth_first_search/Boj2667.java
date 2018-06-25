package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 2667번 : 단지번호 붙이기
 *
 *	@see https://www.acmicpc.net/problem/2667
 *
 */
public class Boj2667 {
	private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0,-1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
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
		ArrayList<Integer> list = new ArrayList<>();				// 가구수를 담을 리스트
		
		for(int row = 0; row < N; row++){							// BFS 실행, 가구가 존재하면서 방문하지 않은 곳
			for(int col = 0; col < N; col++){
				if(map[row][col] == 1 && !isVisited[row][col]){
					isVisited[row][col] = true;
					
					Queue<Point> queue = new LinkedList<>();	
					queue.offer(new Point(row, col));
					
					areaCnt++;								// 단지 수 증가
					int nodeCnt = 1;
					
					while(!queue.isEmpty()){
						Point current = queue.poll();
						
						for(final int[] DIRECTION : DIRECTIONS){
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N){
								if(map[nextRow][nextCol] == 1 && !isVisited[nextRow][nextCol]){
									isVisited[nextRow][nextCol] = true;
									nodeCnt++; 													// 가구의 수 증가 
									queue.offer(new Point(nextRow, nextCol));
								}
							}
						}
					}
					list.add(nodeCnt);					// 가구 수 저장
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(areaCnt).append(NEW_LINE);		// 각 결과들을 정리해 버퍼에 담은 후
		
		Collections.sort(list);
		
		for(int nodeCnt : list) {
			sb.append(nodeCnt).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *	정점 이너 클래스
	 *
	 */
	private static class Point{
		public int row;
		public int col;
		
		public Point(int row, int col){
			this.col = col;
			this.row = row;
		}
	}

}
