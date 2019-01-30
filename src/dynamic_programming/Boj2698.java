package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2698번: 인접한 비트의 갯수
 *
 *	@see https://www.acmicpc.net/problem/2698/
 *
 */
public class Boj2698 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		int[][][] dp = dynamicProgramming();
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
		
			sb.append(dp[n][k][1] + dp[n][k][0]).append(NEW_LINE);		// n자리의 인접한 비트갯수 k개에 해당하는 값
		}
		
		System.out.println(sb);
	}
	
	private static int[][][] dynamicProgramming(){
		int[][][] arr = new int[101][101][2];	// [자릿수][인접 비트 수(n - 1)][끝자리에 들어오는 수 0 or 1] = 집합의 갯수
		
		arr[1][0][0] = arr[1][0][1] = 1;		// 1자리수의 인접 비트 갯수가 0개, 1개의 갯수
		
		/**
		 * 
		 * 		현재 xxxxx
		 * 			xxx00, xxx10
		 * 			xxx01, xxx11 (1110 -> 11101, 1100 -> 11011)
		 */
		for(int i = 2; i < 101; i++) {
			for(int j = 0; j < i; j++) {
				arr[i][j][0] = arr[i - 1][j][0] + arr[i - 1][j][1];		// 맨 뒤에 0이 오는 경우 = 이전 자리에서 0이 오는 경우 + 이전 자리에서 1이 오는 경우
				// 맨 뒤에 1이 오는 경우 = 이전 자리에서 0이 오는 경우 + 이전 자리에서 1이 오는 경우의 인접 비트수가 1 작은 경우
				arr[i][j][1] = arr[i - 1][j][0] + (j == 0 ? 0 : arr[i - 1][j - 1][1]);
			}
		}
		
		return arr;
	}
}
