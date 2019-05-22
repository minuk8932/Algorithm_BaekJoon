package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 17210번: 문문문
 *
 *	@see https://www.acmicpc.net/problem/17210/
 *
 */
public class Boj17210 {
	private static final String NO_WAY = "Love is open door\n";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		int door = Integer.parseInt(br.readLine());
		
		System.out.print(N > 5 ? NO_WAY: getWay(N, door == 1 ? false: true));		// 문제 조건의 따라 2,3의 최소 공배수부터 탈출 불가
	}
	
	private static StringBuilder getWay(long n, boolean flag) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < n; i++) {
			sb.append(flag ? 1: 0).append(NEW_LINE);		// 문여는 방법
			flag = !flag;
		}
		
		return sb;
	}
}
