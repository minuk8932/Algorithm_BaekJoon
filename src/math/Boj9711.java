package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9711번: 피보나치 수
 *
 *	@see https://www.acmicpc.net/problem/9711/
 *
 */
public class Boj9711 {
	private static final String NEW_LINE = "\n";
	private static final String CASE = "Case #";
	private static final String COLON = ": ";
	
	private static long[] fibo = new long[10_001];
	private static int checkPoint;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		getfinbonacci();

		for (int i = 1; i < T + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());

			sb.append(CASE).append(i).append(COLON).append(P <= checkPoint ? fibo[P] % Q : moreFibo(P, Q)).append(NEW_LINE);
		}

		System.out.println(sb);
	}
	
	private static void getfinbonacci() {
		fibo[1] = 1;
		fibo[2] = 1;

		for(int i = 3; i < fibo.length; i++) {
			if(fibo[i - 1] + fibo[i - 2] < 0) break;
			fibo[i] = fibo[i - 1] + fibo[i - 2];
			
			checkPoint = i;			// 최종 양수 피보나치
		}
	}
	
	private static long moreFibo(int idx, int mod) {
		for(int start = checkPoint + 1; start < idx + 1; start++) {			// 음수가 되는 피보 나치부터 하나씩 계산
			fibo[start] = (fibo[start - 1] % mod + fibo[start - 2] % mod) % mod;
		}
		
		return fibo[idx];
	}
}