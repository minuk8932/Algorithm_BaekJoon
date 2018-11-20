package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16504번: 종이 접기
 *
 *	@see https://www.acmicpc.net/problem/16504/
 *
 */
public class Boj16504 {
	public static void main(String[] args) throws Exception{
		System.out.println(input());
	}
	
	/**
	 * 색종이를 접어나간 후의 총합 -> 배열의 전체 합
	 * 
	 */
	private static long input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long sum = 0;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				sum += Long.parseLong(st.nextToken());
			}
		}
		
		return sum;
	}
}
