package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6124번: Good Grass
 *
 *	@see https://www.acmicpc.net/problem/6124/
 *
 */
public class Boj6124 {
	private static Point res = new Point(-1, -1, -1);
	private static int[][] map = null;
	
	private static final char NEW_LINE = '\n';
	private static final char SPACE = ' ';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		search(R, C);		// 탐색 메소드를 통해 최댓 값과 그때의 정점을 찾음
		
		StringBuilder sb = new StringBuilder();
		sb.append(res.sum).append(NEW_LINE);						// 찾은 값을 버퍼에 담고
		sb.append(res.row + 1).append(SPACE).append(res.col + 1);
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Point{
		int row;
		int col;
		int sum;
		
		public Point(int row, int col, int sum){
			this.row = row;
			this.col = col;
			this.sum = sum;
		}
	}
	
	/**
	 * 설정 할 수 있는 정점들을 이용하는 메소드
	 * 
	 */
	private static void search(int r, int c) {
		int max = 0;
		
		for(int i = 0; i < r - 3; i++) {
			for(int j = 0; j < c - 3; j++) {		// 왼쪽 상단 정점을 출력하기 위한 반복문 설정
				int sum = getSum(i, j);
				
				if(max < sum) {				// getSum 메소드를 통해 나오는 최댓값에 따라 클래스 변수 내의 값을 바꿔줌
					max = sum;
					
					res = new Point(i, j, max);
				}
			}
		}
	}
	
	/**
	 * 임의의 정점에 해당하는 범위 내의 값의 합을 반환
	 * 
	 */
	private static int getSum(int row, int col) {
		int sum = 0;
		
		for(int i = row; i < row + 3; i++) {
			for(int j = col; j < col + 3; j++) {
				sum += map[i][j];
			}
		}
		
		return sum;
	}
}
