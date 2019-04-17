package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15990번: 1, 2, 3 더하기 3
 *
 *	@see https://www.acmicpc.net/problem/15990/
 *
 */
public class Boj15990 {
	private static final int MOD = 1_000_000_009;
	private static final int MAX = 100_001;
	private static final String NEW_LINE = "\n";
	
	private static long[][] token = new long[MAX][4];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		init();
		
		while(T-- > 0){
			int n = Integer.parseInt(br.readLine());
			sb.append(((token[n][1] % MOD) + (token[n][2] % MOD) + (token[n][3] % MOD)) % MOD).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static void init() {
		token[1][1] = 1;				// 가장 말단의 세개의 경우의 수
		token[2][2] = 1;
		token[3][1] = 1;				// 연속은 안되므로 21, 12 두가지
		token[3][2] = 1;
		token[3][3] = 1;				// 3 한가지

		for(int i = 4; i < MAX; i++){	// 10만 까지 미리 모두 구함
			token[i][1] = ((token[i - 1][2] % MOD) + (token[i - 1][3] % MOD)) % MOD;
			token[i][2] = ((token[i - 2][1] % MOD) + (token[i - 2][3] % MOD)) % MOD;
			token[i][3] = ((token[i - 3][2] % MOD) + (token[i - 3][1] % MOD)) % MOD;
		}
	}
}
