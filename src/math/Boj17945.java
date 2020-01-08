package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17945번: 통학의 신
 *
 *	@see https://www.acmicpc.net/problem/17945/
 *
 */
public class Boj17945 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		System.out.println(getValue(A, B));
	}
	
	private static String getValue(int a, int b) {
		StringBuilder sb = new StringBuilder();
		
		int sum = -a * 2;
		int mul = b;
		
		for(int i = -1_000; i < 1_001; i++) {
			int diff = sum - i;							// find
			
			if(diff * i == mul) {
				if(diff < i) sb.append(diff).append(" ").append(i);			// ordering
				else if(diff > i) sb.append(i).append(" ").append(diff);
				else sb.append(diff);
				break;
			}
		}
		
		return sb.toString();
	}
}
