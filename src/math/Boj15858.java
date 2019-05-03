package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15858번: Simple Arithmetic
 *
 *	@see https://www.acmicpc.net/problem/15858/
 *
 */
public class Boj15858 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		long c = Long.parseLong(st.nextToken());
		
		long mul = a * b;
		long div = mul / c;
		long mod = mul % c;
		
		StringBuilder sb = new StringBuilder();
		int loop = 10;
		
		while(loop-- > 0) {					// 뒤에 소수점 구하기
			if(mod % c != 0) {
				if(mod > c) {
					sb.append(mod / c);
					mod %= c;
				}
				else {
					mod *= 10;
					sb.append(mod / c);
					mod %= c;
				}
			}
			else {
				sb.append(0);
			}
		}
		
        System.out.println(div + "." + (mul % c == 0 ? "0000000": sb));		// 결과
    }
}