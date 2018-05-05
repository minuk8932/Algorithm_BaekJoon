package breadth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2799번: 블라인드
 *
 *	@see https://www.acmicp.net/problem/2799/
 *
 */
public class Boj2799 {
	private static final int[] WINDOWS = {16, 12, 8, 4, 0};
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final String SPACE = " ";
	private static final char OPENED = '.';
		
	private static char[][] apart = null;
	private static int[] sort = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int row = N * 5 + 1;					// 행과 열의 전체 사이즈를 구함
		int col = M * 5 + 1;
		
		sort = new int[N * M];
		apart = new char[row][col];
		
		for(int i = 0; i < row; i++){
			String line = br.readLine();
			
			for(int j = 0; j < col; j++){
				apart[i][j] = line.charAt(j);
			}
		}
		
		bfs(row, col);				// 너비 우선 탐색 알고리즘 실행
		
		int[] res = new int[5];
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < WINDOWS.length; i++){		// 해당 창문 모양에 . 수가 같은것이 존재하면 그 배열에 해당하는 인덱스 + 1;
			for(int j = 0; j < sort.length; j++){
				if(sort[j] == WINDOWS[i]){
					res[i]++;
				}
			}
			
			sb.append(res[i]).append(SPACE);		// 결과를 인덱스별로 하나씩 버퍼에 담고
		}
		
		System.out.println(sb.toString());		// 한번에 출력
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
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
	
	/**
	 * 너비 우선 탐색 알고리즘 메소드
	 * @param rowSize: 행의 최대 사이즈
	 * @param colSize: 열의 최대 사이즈
	 */
	private static void bfs(int rowSize, int colSize){
		int max = 0;			// 각 창문별 사이즈를 구할 변수
		int idx = 0;				// 구한 사이즈를 담아줄 배열을 채우기 위한 인덱스
		int[][] isVisited = new int[rowSize][colSize];	// 방문 확인 정수형 배열
		
		for(int row = 0; row < rowSize; row++){
			for(int col = 0; col < colSize; col++){
				if(apart[row][col] == OPENED && isVisited[row][col] == 0){		// 창문의 열려있는 부분 그리고, 아직 탐색하지 않은 창문만 탐색
					isVisited[row][col] = 1;						// 해당 .을 방문함으로 바꾸고
					
					Queue<Point> q= new LinkedList<>();
					q.offer(new Point(row, col));				// 현재 위치를 큐에담음
					
					while(!q.isEmpty()){
						Point current = q.poll();
						
						for(final int[] DIRECTION : DIRECTIONS){
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow >= 0 && nextRow < rowSize && nextCol >= 0 && nextCol < colSize){		// 다음 탐색할 점은 창문의 범위 내에서
								if(isVisited[nextRow][nextCol] == 0 && apart[nextRow][nextCol] == OPENED){		// 방문하지 않았으면서 열려있는 부분인 경우
									isVisited[row][col]++;						// 창문의 사이즈를 구하기위해 초기 배열의 값을 +1씩함
									isVisited[nextRow][nextCol] = 1;			// 다시 방문하지 않기 위해 1로 초기화
									
									max = Math.max(isVisited[row][col], max);		// 현재 탐색하는 창문의 완전한 사이즈를 구함
									
									q.offer(new Point(nextRow, nextCol));	// 다음 탐색을 위해 인덱스를 큐에 담음
								}
							}
						}
					}
					
					if(max != 0 && sort[idx] == 0){		// 너비 우선탐색이 끝난후 배열에 값이 아직 들어가지 않았고, 창문의 사이즈가 0이 아닌경우에만
						sort[idx] = max;					// 값을 담고 인덱스를 뒤로 밀어줌, 만약 창문의 사이즈가 0인경우는 자동으로 초기화 안된 배열의 수로 정해짐
						idx++;
						
						max = 0;		// 최댓값 초기화
					}	
				}
			}
		}
	}
}
