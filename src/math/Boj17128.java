package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17128번: 소가 정보섬에 올라온 이유
 *
 *	@see https://www.acmicpc.net/problem/17128/
 *
 */
public class Boj17128 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[] cows = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			cows[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] set = init(N, cows);
		int total = 0;
		
		for(int i = 0; i < N; i++) {
			total += set[i];
		}
		
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		
		while(Q-- > 0) {
			int index = Integer.parseInt(st.nextToken()) - 1;
			
			for(int i = 0; i < 4; i++) {
				int target = index - i;
				target = target < 0 ? N + target: target;		// 인덱스가 음수인 경우
				
				set[target] *= -1;						// 해당 곱 집합을 -1로 변경
				total += set[target] * 2;				// 더할 땐 2배
			}
			
			sb.append(total).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int[] init(int n, int[] arr) {
		int[] res = new int[n];
		Arrays.fill(res, 1);
		
		for(int i = 0; i < 4; i++) {
			res[0] *= arr[i];
		}
		
		for(int i = 1; i < n; i++) {								// 각 배열의 값을 4개의 곱으로 초기화
			res[i] = (res[i - 1] * arr[(i + 3) % n]) / arr[i - 1];
		}
		
		return res;
	}
}
