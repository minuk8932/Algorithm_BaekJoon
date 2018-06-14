package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5624번: 좋은 수
 *
 *	@see https://www.acmicpc.net/problem/5624/
 *
 */
public class Boj5624 {
	private static final int INF = 400_001;
	private static final int SEPERATE = 200_000;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int res = 0;
		int[] A = new int[N];
		boolean[] sum = new boolean[INF];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {		// 음수 양수를 SEPERATE 기준으로 구분
			int tmp = A[i];
			
			for (int j = 0; j < i; j++) {
				if (sum[tmp - A[j] + SEPERATE]) {	// sum 배열이 참이면 결과값 +1
					res++;
					break;
				}
			}
	        
			for (int j = 0; j <= i; j++) {			// j는 i전까지의 인덱스에 해당하는 값을 더한 결과에 해당하는 인덱스의 값을 참으로 변경
				sum[tmp + A[j] + SEPERATE] = true;
			}
		}
		
		System.out.println(res);		// 결과값 출력
	}
}
