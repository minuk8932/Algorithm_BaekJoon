package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 	
 * 	@author exponential-e
 *	백준 2725번: 보이는 점의 갯수
 *
 *	@see https://www.acmicpc.net/problem/2725/
 *
 */
public class Boj2725 {
	private static final String NEW_LINE = "\n";
	private static int[] dots = new int[1_001];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int C = Integer.parseInt(br.readLine());
		
		countDots();
		
		while(C-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append(dots[N]).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
	
	private static void countDots() {
		dots[1] = 3;
		
		for(int i = 2; i < dots.length; i++) {
			int count = 0;
			
			for(int j = 1; j < i; j++) {
				count += gcd(i, j) == 1 ? 1: 0;		// 서로소인 경우 무조건 보이는 점
			}
			
			dots[i] = dots[i - 1] + 2 * count;		// 현재 점의 갯수 * 2 (대각선 기준 양쪽) + 이전 점의 갯수
		}
	}
	
	private static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a % b);
	}
}
