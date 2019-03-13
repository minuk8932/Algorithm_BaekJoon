package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11694번: 님 게임
 *
 *	@see https://www.acmicpc.net/problem/11694/
 *
 */
public class Boj11694 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] stones = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			stones[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(winner(n, stones) ? "koosaga": "cubelover");
	}
	
	private static boolean winner(int n, int[] arr) {
		boolean oneChecker = false;
		int result = 0;
		
		for(int i = 0; i < n; i++) {
			if(arr[i] == 1) oneChecker = true;	// 1이 등장?
			result ^= arr[i];					// 항등원 기준 돌 무더기의 값을 모두 xor
		}
		
		if(oneChecker) result--;
		return result != 0 ? true : false;
	}
}
