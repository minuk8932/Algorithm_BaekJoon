package breadth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14052번 : 연구소
 *
 *	@see https://www.acmicpc.net/problem/14052/
 *
 */
public class Boj14502 {
	private static final String SPACE = " ";
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int VIRUS = 2;
	private static final int BLOCK = 1;
	private static final int SAFETY = 0;
	
	private static Point[] vlist = null;
	private static int[][] map = null;
	private static int[][] tmp = null;
	private static int N = 0;
	private static int M = 0;
	private static int loop = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tmp = new int[N][M];
		map = new int[N][M];
		vlist = new Point[11];
		
		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			for(int j = 0; j < M; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
				tmp[i][j] = map[i][j];
				
				if(map[i][j] == VIRUS){					// 바이러스 위치를 포인트 배열에 담아줌
					vlist[loop] = new Point(i, j);
					loop++;
				}
			}
		}
		
		int max = 0;
		
		for(int row1 = 0; row1 < N; row1++){
			for(int col1 = 0; col1 < M; col1++){
				if(map[row1][col1] != SAFETY) continue;						// 안전지역아닌경우 넘어감
				
				for(int row2 = 0; row2 < N; row2++){
					for(int col2 = 0; col2 < M; col2++){
						if(map[row2][col2] != SAFETY) continue;					// 안전지역아닌경우 넘어감
						
						for(int row3 = 0; row3 < N; row3++){
							for(int col3 = 0; col3 < M; col3++){
								if(map[row3][col3] != SAFETY) continue;					// 안전지역아닌경우 넘어감
								
								if(row1 == row2 && col1 == col2) continue;					// 벽이 겹친경우 넘어감
								if(row2 == row3 && col2 == col3) continue;
								if(row3 == row1 && col3 == col1) continue;
								
								map[row1][col1] = BLOCK;			// 모든 경우의 수의 벽을 세움
								map[row2][col2] = BLOCK;
								map[row3][col3] = BLOCK;
								
								int res = bfs();						// 벽을 세운 후 너비 우선 탐색을 통해 바이러스를 전파시킴
								
								if(res > max){						// 안전지역의 최대 크기를 구함
									max = res;
								}
								
								reset();					// 배열을 초기화시키고 반복
							}
						}
					}
				}
			}
		}
		
		System.out.println(max);			// 안전지역 최댓값 출력
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
	 * 너비 우선 탐색 알고리즘
	 * @return : 안전지역의 최대 크기 반환
	 */
	private static int bfs(){
		int space = 0;
		
		for(int i = 0; i < loop; i++){				
			Queue<Point> q = new LinkedList<>();
			q.offer(new Point(vlist[i].row, vlist[i].col));			// 바이러스 위치만 찾아내서
				
			while(!q.isEmpty()){
				Point current = q.poll();
					
				for(final int[] DIRECTION : DIRECTIONS){
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
						
					if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M){
						if(map[nextRow][nextCol] == SAFETY){											// 다음 인접한 곳이 안전지역이면
							map[nextRow][nextCol] = VIRUS;										// 바이러스를 퍼트리고
								
							q.offer(new Point(nextRow, nextCol));					// 퍼진 지역으로부터 다시 탐색
						}
					}
				}
			}
		}
		
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				if(map[i][j] == SAFETY){			// 안전지역의 갯수를 구함 
					space++;
				}
			}
		}
		
		return space;			// 안전지역 크기 반환
	}
	
	/**
	 * 배열 초기화 메소드
	 */
	private static void reset(){
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				map[i][j] = tmp[i][j];
			}
		}

	}
}
