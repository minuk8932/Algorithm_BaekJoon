package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17203번: Sum Differentiation Easy Max
 *
 *	@see https://www.acmicpc.net/problem/17203/
 *
 */
public class Boj17203 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[] differ = new int[N];
		st = new StringTokenizer(br.readLine());
		
		int prev = Integer.parseInt(st.nextToken());
		for(int i = 1; i < N; i++) {
			int post = Integer.parseInt(st.nextToken());
			differ[i] = Math.abs(prev - post);				//  변화량
			
			prev = post;
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			if(from == to) {
				sb.append(0).append(NEW_LINE);
				continue;
			}
			
			sb.append(sectionSum(differ, from, to)).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static int sectionSum(int[] arr, int s, int e) {
		int sum = 0;
		
		for(int i = s + 1; i < e + 1; i++) {		// 변화량 합
			sum += arr[i];
		}
		
		return sum;
	}
}
