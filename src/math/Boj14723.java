package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 14723번: 이산수학 과제
 *
 *	@see https://www.acmicpc.net/problem/14723/
 *
 */
public class Boj14723 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(process(N));
	}
	
	private static StringBuilder process(int n) {
		StringBuilder sb = new StringBuilder();
		int start = 1;
		
		if(start == n) return sb.append(1).append(SPACE).append(1);
		
		int count = 2;
		while(start < n) {			// 해당 위치의 열까지의 값
			start += count;
			count++;
		}
		
		int b = count - 1;
		int a = 1;
		
		while(start != n) {			// 해당 열에서의 위치
			start--;
			b--;
			a++;
		}
		
		return sb.append(a).append(SPACE).append(b);
	}
}
