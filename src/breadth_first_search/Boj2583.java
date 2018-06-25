package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2583번 : 영역 구하기
 *
 *	@see https://www.acmicpc.net/problem/2583
 *
 */
public class Boj2583 {
	private static final String SPACE = " ";
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] area = new int[N][M];

		while (K-- > 0) {
			st = new StringTokenizer(br.readLine(), SPACE);
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			
			for(int i = B; i < D; i++){					// 문제의 영역을 미리처리 해놓고
				for(int j = A; j < C; j++){
					area[i][j] = 1;
				}
			}
		}
		
		int[][] isVisited = new int[N][M];
		
		ArrayList<Integer> list = new ArrayList<>();		// 영역의 넓이를 담을 리스트
		
		int areaCnt = 0;
		
		StringBuilder sb = new StringBuilder();
		
		for(int row = 0; row < N; row++){		// BFS 실행
			for(int col = 0; col < M; col++){
				if(area[row][col] == 0 && isVisited[row][col] == 0){
					isVisited[row][col] = 1;
					
					int areaWidth = 1;
					
					areaCnt++;
					
					Queue<Point> q = new LinkedList<>();
					q.offer(new Point(row, col));
					 
					while(!q.isEmpty()){
						Point current = q.poll();
						
						for(final int[] DIRECTION : DIRECTIONS){
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M){
								if(area[nextRow][nextCol] == 0 && isVisited[nextRow][nextCol] == 0){
									isVisited[nextRow][nextCol] = 1;
									
									areaWidth += isVisited[row][col];
									q.offer(new Point(nextRow, nextCol));
								}
							}
						}
					}
					list.add(areaWidth);
				}
			}
		}
		
		int size = list.size();
		int[] cnt = new int[size];
		
		for(int i = 0; i < cnt.length; i++){		// 리스트의 값들을 배열로 옮김
			cnt[i] = list.get(i);
		}
		Arrays.sort(cnt);							// 배열 내부 정렬
		
		for(int i = 0; i < cnt.length; i++){
			sb.append(cnt[i]).append(SPACE);
		}
		
		System.out.println(areaCnt);
		System.out.println(sb.toString());	// 결과값 한번에 출력
		
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *	정점 이너 클래스
	 *
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
