package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5549번: 행성 탐사
 *
 *	@see https://www.acmicpc.net/problem/5549/
 *
 */
public class Boj5549 {
	private static int[][][] planet = null;
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final char JUNGLE = 'J';
	private static final char OCEAN = 'O';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		planet = new int[M + 1][N + 1][3];
		for(int i = 1; i < M + 1; i++) {
			String line = br.readLine();
			
			for(int j = 1; j < N + 1; j++) {
				char g = line.charAt(j - 1);
				
				if(g == JUNGLE) planet[i][j][0]++;
				else if(g == OCEAN) planet[i][j][1]++;
				else planet[i][j][2]++;
			}
		}
		
		initMap(M, N);
		
		StringBuilder sb = new StringBuilder();
		
		while(K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int geo = 0; geo < 3; geo++) {			// 최상단 사각형 		좌측끝 사각형					최하단 모서리
				sb.append(planet[x2][y2][geo] - planet[x1-1][y2][geo] - planet[x2][y1-1][geo] + planet[x1-1][y1-1][geo]).append(SPACE);
			}
			
			sb.append(NEW_LINE);
		}
	
		System.out.print(sb.toString());
	}
	
	private static void initMap(int n, int m) {
		for(int row = 1; row < n + 1; row++) {				// 각 배열의 위치 정보 저장
			for(int col = 1; col < m + 1; col++) {
				for(int geo = 0; geo < 3; geo++) {
					planet[row][col][geo] += planet[row - 1][col][geo] + planet[row][col - 1][geo] - planet[row - 1][col - 1][geo];
				}
			}
		}
	}
}
