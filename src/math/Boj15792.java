package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15792번: A / B - 2
 *
 *	@see https://www.acmicpc.net/problem/15792/
 *
 */
public class Boj15792 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		System.out.println(getValue(A, B));
	}
	
	private static StringBuilder getValue(int a, int b) {
		StringBuilder sb = new StringBuilder();
		boolean flag = true;
		int under = 0;
		
		while(true) {					// 나눗셈 공식
			if(!flag) under++;
			
			sb.append(a / b);
			if(a % b == 0) break;			// 나누어 떨어지면 종료
			a %= b;
			
			if(a / b == 0) {
				if(flag) {
					sb.append(".");
					flag = false;
				}
			}
			
			if(a < b) a *= 10;
			if(under > 1000) break;			// 소숫점 1000번째 자리에서 종료
		}
		
		return sb;
	}
}
