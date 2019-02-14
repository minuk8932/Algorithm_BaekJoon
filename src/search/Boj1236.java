package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1236번: 성 지키기
 *
 *	@see https://www.acmicpc.net/problem/1236/
 *
 */
public class Boj1236 {
	private static final char SUPERVISE = 'X';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[2][50];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				char c = line.charAt(j);
				
				if(c == SUPERVISE) map[0][i] = map[1][j] = true;	// 경비원 채우기
			}
		}
		
		System.out.println(positioning(N, M, map));
	}
	
	private static int positioning(int n, int m, boolean[][] map){
		int[] count = new int[2];
		
		for(int i = 0; i < n; i++) {	// 필요한 경비원 수 체크
			if(!map[0][i]) count[0]++;
		}
			
		for(int i = 0; i < m; i++) {
			if(!map[1][i]) count[1]++;
		}
		
		return count[0] < count[1] ? count[1] : count[0];
	}
}
