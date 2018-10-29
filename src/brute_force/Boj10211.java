package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10211번: Maximum Subarray
 *
 *	@see https://www.acmicpc.net/problem/10211/
 *
 */
public class Boj10211 {
	private static final String NEW_LINE = "\n";
	private static final int INF = -1_000_000;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int max = INF;
			for(int i = 0; i < N; i++) {
				int tmp = arr[i];
				
				for(int j = i + 1; j < N; j++) {		// 각 값마다 비교, 단 한개의 값이 가장 큰 값일수도 있으므로 조건문을 통해 점검
					if(max < tmp) max = tmp;
					
					tmp += arr[j];
					if(max < tmp) max = tmp;
				}
			}
			
			sb.append(Math.max(max, arr[N - 1])).append(NEW_LINE);	// 가장 마지막의 값과 구한 값의 최대를 버퍼에 저장
		}
		
		System.out.println(sb);		// 결과 값 한번에 출력
	}
}
