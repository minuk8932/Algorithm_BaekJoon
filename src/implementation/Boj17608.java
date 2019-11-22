package implementation;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * 
 *	@author exponential-e
 *	백준 17608번: 막대기
 * 
 * 	@see https://www.acmicpc.net/problem/17608/
 * 
 */
public class Boj17608 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] pillar = new int[N];
		for(int i = 0; i < N; i++) {
			pillar[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(see(N, pillar));
	}
	
	private static int see(int n, int[] arr) {
		int count = 1;
		int head = arr[arr.length - 1];
		
		for(int i = arr.length - 2; i >= 0; i--) {
			if(head < arr[i]) {
				count++;				// count
				head = arr[i];
			}
		}
		
		return count;
	}
}
