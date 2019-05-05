package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17174번: 전체 계산 횟수
 *
 *	@see https://www.acmicpc.net/problem/17174/
 *
 */
public class Boj17174 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		System.out.println(getCost(N, M, N));
	}
	
	private static int getCost(int n, int m, int value) {
		if(n < m) return value;
		return getCost(n / m, m, value + (n / m));		// 몫들의 합
	}
}
