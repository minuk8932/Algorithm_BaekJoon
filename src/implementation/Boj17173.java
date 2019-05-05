package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17173번: 배수들의 합
 * 
 *	@see https://www.acmicpc.net/problem/17173/
 *
 */
public class Boj17173 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] source = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			source[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getSum(N, M, source));
	}
	
	private static int getSum(int n, int m, int[] src) {
		boolean[] values = new boolean[1_001];
		
		for(int i = 0; i < m; i++) {
			int target = src[i];
			int mul = 1;
			
			while(target * mul <= n) {			// 배수들 구하기
				values[target * mul] = true;
				mul++;
			}
		}
		
		int sum = 0;
		for(int i = 1; i < values.length; i++) {
			if(values[i]) sum += i;
		}
		
		return sum;
	}
}
