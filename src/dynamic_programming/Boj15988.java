package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15988번: 1, 2, 3 더하기 3
 *
 *	@see https://www.acmicpc.net/problem/15988/
 *
 */
public class Boj15988 {
	private static final int MOD = 1_000_000_009;
	private static final int MAX = 1_000_001;
	private static final String NEW_LINE = "\n";
	
	private static long[] token = new long[MAX];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		init();
		
		while(T-- > 0){
			int n = Integer.parseInt(br.readLine());
			sb.append(token[n]).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static void init() {
		token[1] = 1;				// 가장 말단의 세개의 경우의 수
		token[2] = 2;
		token[3] = 4;
		
		for(int i = 4; i < MAX; i++){		// 100만 까지 미리 모두 구함
			token[i] = ((token[i-3] % MOD) + (token[i-2] % MOD) + (token[i-1] % MOD)) % MOD;
		}
	}
}
