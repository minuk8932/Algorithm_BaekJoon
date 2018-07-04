package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6318번: Box of Bricks
 *
 *	@see https://www.acmicpc.net/problem/6318/
 *
 */
public class Boj6318 {
	private static final String SET = "Set #";
	private static final String POST = "The minimum number of moves is ";
	private static final String NEW_LINE = "\n";
	private static final String DOT = ".";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int seq = 1;
		
		while(true) {
			int n = Integer.parseInt(br.readLine());
			
			if(n == 0) break;
			
			int[] nums = new int[n];
			int sum = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
				sum += nums[i];			// 총합을 구함
			}
			
			int res = 0;
			sum /= n;
			
			for(int i = 0; i < n; i++) {
				int diff = sum - nums[i];
				
				if(diff > 0) res += diff;		// 옮길 횟수를 구하고
			}
			
			// 조건에 따라 값을 버퍼에 담음
			sb.append(SET).append(seq).append(NEW_LINE);
			sb.append(POST).append(res).append(DOT).append(NEW_LINE).append(NEW_LINE);
            seq++;
		}		
		
		System.out.println(sb.toString());	// 결과 값 한번에 출력
	}
}
