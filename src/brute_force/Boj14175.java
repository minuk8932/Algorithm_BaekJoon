package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14175번: Cow - signal
 *
 *	@see https://www.acmicpc.net/problem/14175/
 *
 */
public class Boj14175 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		char[][] picture = new char[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				picture[i][j] = line.charAt(j);
			}
		}
		
		System.out.println(redraw(N, M, K, picture));
	}
	
	private static StringBuilder redraw(int n, int m, int k, char[][] pic) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			StringBuilder line = new StringBuilder();
			
			for(int j = 0; j < m; j++) {
				for(int dup = 0; dup < k; dup++) {
					line.append(pic[i][j]);				// i, j 데이터 k개 만큼 생성
				}
			}
			
			for(int du = 0; du < k; du++) {
				sb.append(line).append(NEW_LINE);		// 만들어진 줄을 k줄 만큼 생성
			}
		}
		
		return sb;
	}
}
