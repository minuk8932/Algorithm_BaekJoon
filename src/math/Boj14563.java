package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14563번: 완전수
 *
 *	@see https://www.acmicpc.net/problem/14563/
 *
 */
public class Boj14563 {
	private static final String P = "Perfect";
	private static final String D = "Deficient";
	private static final String A = "Abundant";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(T-- > 0) {
			int result = process(Integer.parseInt(st.nextToken()));
			sb.append(result < 0 ? D: result == 0 ? P: A).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int process(int n) {
		int sum = 0;
		
		for(int i = 1; i < n; i++) {
			if(n % i == 0) sum += i;
			if(sum > n) return 1;
		}
		
		return sum < n ? -1: 0;
	}
}
