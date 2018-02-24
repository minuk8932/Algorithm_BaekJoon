package breadth_first_serch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1012번 : 유기농 배추
 *
 *	@see https://www.acmicpc.net/problem/1012
 *
 */
public class Boj1012 {
	private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0,-1}};
	private static final int ROW = 0;	// 0 index is row
	private static final int COL = 1;	// 1 index is col
	
	public static final String NEW_LINE = "\n";
	public static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			boolean[][] map = new boolean[N][M];						// 배추 배열 선언
			
			while(K-- > 0){
				st  = new StringTokenizer(br.readLine(), SPACE);
				int col = Integer.parseInt(st.nextToken());
				int row = Integer.parseInt(st.nextToken());
				map[row][col] = true;
			}
			
			int areaCnt = 0;								// 필요한 배추 흰 지렁이 갯수
			
			for(int row = 0; row < N; row++){
				for(int col = 0; col < M; col++){
					if(map[row][col]){					// 배추가 심어져있는지?
						map[row][col] = false;
						areaCnt++;						// 심어져있는 경우 배추 흰지렁이 갯수 + 1, 방문한것으로 표시
						
						Queue<Point> queue = new LinkedList<>();
						queue.offer(new Point(row, col));
						
						while(!queue.isEmpty()){
							Point current = queue.poll();
							
							for(final int[] DIRECTION : DIRECTIONS){
								int nextRow = current.row + DIRECTION[ROW];
								int nextCol = current.col + DIRECTION[COL];
								
								if( 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M){
									if(map[nextRow][nextCol]){								// 다음 방문 할 칸도 배추가 심어져 있는가
										map[nextRow][nextCol] = false;						// 방문한것으로 표시, 인접한 영역별로 각 1마리의 지렁이가 필요하므로 여기선 +1 연산을 하지 않음
										queue.offer(new Point(nextRow, nextCol));
									}
								}
							}
						}
						
					}
				}
			}
			sb.append(areaCnt).append(NEW_LINE);										// 각 영역별 배추 흰지렁이 갯수 표시
		}
		br.close();
		
		System.out.println(sb.toString());													// 결과 값 한번에 출력
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
			this.row = row;
			this.col = col;
		}
	}

}
