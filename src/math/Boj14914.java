package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14914번: 사과와 바나나 나눠주기
 *
 *	@see https://www.acmicpc.net/problem/14914/
 *
 */
public class Boj14914 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		System.out.print(divide(a, b));
	}
	
	private static StringBuilder divide(int apple, int banana) {
		StringBuilder sb = new StringBuilder();
		int friend = 1;
		int cut = Math.min(apple, banana);
		
		while(friend <= cut) {
			if(apple % friend == 0 && banana % friend == 0) {		// 두 수의 공약수인 경우
				sb.append(friend).append(SPACE).append(apple / friend).append(SPACE).append(banana / friend).append(NEW_LINE);
			}
			
			friend++;
		}
		
		return sb;
	}
}
