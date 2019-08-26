package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 9272번: 상근이의 아이디어
 *
 *	@see https://www.acmicpc.net/problem/9272/
 *
 */
public class Boj9272 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n1 = Integer.parseInt(st.nextToken());
		int n2 = Integer.parseInt(st.nextToken());
		
		System.out.println(result(n1, n2));
	}
	
	private static int result(int n1, int n2) {
		int count = n2 - n1;
		int sum = (count + 1) * count / 2;			// 죄다 서로소
		return sum;
	}
}
