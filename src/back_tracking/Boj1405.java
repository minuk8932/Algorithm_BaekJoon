package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1405번: 미친 로봇
 *
 *	@see https://www.acmicpc.net/problem/1405/
 *
 */
public class Boj1405 {
	private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static boolean[][] isVisited = new boolean[30][30];
	private static double result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		double[] prob = new double[4];
		for(int i = 0; i < 4; i++) {
			prob[i] = Double.parseDouble(st.nextToken()) * 0.01;
		}
		
		backTracking(N, prob, 15, 15, 0, 1.0);		// 단순하다 -> 미친 로봇이 지났던 지점 map[row][col]을 다시 방문하지 않는 경우
		System.out.println(result);
	}
	
	private static void backTracking(int n, double[] arr, int row, int col, int depth, double partSum) {
		if(depth == n) {
			result += partSum;		// n번째에서 중복 방문없이 도달 -> 그떄의 확률이 단순한 확률
			return;
		}
		
		if(isVisited[row][col]) return;
		isVisited[row][col] = true;
		
		for(int idx = 0; idx < 4; idx++) {
			int nextRow = row + DIRECTIONS[idx][ROW];
			int nextCol = col + DIRECTIONS[idx][COL];
			if(isVisited[nextRow][nextCol]) continue;
			
			backTracking(n, arr, nextRow, nextCol, depth + 1, partSum * arr[idx]);
			isVisited[nextRow][nextCol] = false;			// 백트래킹
		}
	}
}
