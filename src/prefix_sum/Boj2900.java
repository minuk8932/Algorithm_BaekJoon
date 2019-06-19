package prefix_sum;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2900번: 프로그램
 *
 *	@see https://www.acmicpc.net/problem/2900/
 *
 */
public class Boj2900 {
	private static final String NEW_LINE = "\n";
	
	private static final int INF = 1_000_001;
	private static int[] a = new int[INF];
	private static int[] adder;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] save = new int[N + 1];
		long[] sum = new long[INF];
		adder = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			a[i] = Integer.parseInt(st.nextToken());
			save[a[i]]++;		// 더해지는 간격의 갯수 저장
		}
		
		sum[0] = K;					// 0번째 배열은 무조건 K가 저장 되어있음
		sum = sieve(N, save, sum);
		
		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		
		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken());
			
			sb.append(from == -1 ? sum[to]: sum[to] - sum[from]).append(NEW_LINE);		// 0으로 시작하면 to까지만 합, 나머지는 부분합 출력
		}
		
		System.out.println(sb.toString());
	}
	
	private static long[] sieve(int n, int[] arr, long[] result) {		
		for(int i = 1; i < n + 1; i++) {
			if(arr[i] == 0) continue;				// 합할 값이 없으면 무시
			
			for(int j = i; j < n + 1; j += i) {
				adder[j] += arr[i];					// 값이 있으면 adder에 저장
			}
		}
		
		for(int i = 1; i < n; i++) {
			result[i] = result[i - 1] + adder[i];	// 최종 sum배열 생성
		}
		
		return result;
	}
}
