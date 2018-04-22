package breadth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11403번 : 경로 찾기
 *
 *	@see https://www.acmicpc.net/problem/11403/
 *
 */
public class Boj11403 {
	private static final String SPACE = " ";
	private static final String END_LINE = "\n";
	
	private static int N = 0;
	private static int[][] map = null;
	private static boolean[][] isVisited = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		isVisited = new boolean[N][N];
		map = new int[N][N];
		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();			// 너비 우선 탐색 알고리즘 실행
		
		System.out.println(res());	// 결과 메소드를 통한 결과 값 출력
	}
	
	/**
	 * 정점 이너 클래스
	 * 	@author minchoba
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
	 *	너비 우선 탐색 알고리즘
	 */
	private static void bfs(){
		for(int row = 0; row < N; row++){
			for(int col = 0; col < N; col++){
				if(map[row][col] == 1 && !isVisited[row][col]){		// 현재 길이 존재하고 방문 전인 경우
					isVisited[row][col] = true;					// 방문함으로 바꾸고
					
					Queue<Point> q = new LinkedList<>();
					q.offer(new Point(row, col));				// 해당 위치를 큐에 담아줌
					
					while(!q.isEmpty()){
						Point current = q.poll();
						
						for(int next = 0; next < N; next++){		// 반복문을 돌려가
							if(map[current.row][current.col] == 1 && map[current.col][next] == 1){	// 조건에 해당하는 행과 열을 찾고
								if(!isVisited[current.row][next]){			// 그때의 합 경로가 방문 전이면
									isVisited[current.row][next] = true;	// 방문함으로 바꾸고
									map[current.row][next] = 1;					// 합경로에도 경로가 존재함으로 바꿔줌
									
									q.offer(new Point(current.row, next));		// 큐에 해당 경로를 담고 다시 너비 우선 탐색 알고리즘 실행
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 결과 출력 메소드
	 * @return : 결과에 해당하는 값들을 문자열로 반환
	 */
	private static String res(){
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				sb.append(map[i][j]).append(SPACE);		// 버퍼를 통해 결과 값들을 형식에 맞게 담아줌
			}
			sb.append(END_LINE);
		}
		
		return sb.toString();		// 결과 반환
	}
}
