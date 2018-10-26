package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11659번: 구간 합 구하기 4
 *
 *	@see https://www.acmicpc.net/problem/11659/
 *
 */
public class Boj11659 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] sum = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) {								// 구간 별 누적 합을 배열에 저장
			sum[i] += (sum[i - 1] + Integer.parseInt(st.nextToken()));
		}
		
		StringBuilder sb = new StringBuilder();
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken());
			
			sb.append(sum[to] - sum[from]).append(NEW_LINE);		// 해당 구간의 합을 구하기 위해 구간의 차를 구해 버퍼에 저장
		}
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
}
