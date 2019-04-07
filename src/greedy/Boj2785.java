package greedy;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 	@author minchoba
 *	백준 2785번: 체인
 *
 *	@see https://www.acmicpc.net/problem/2785/
 *
 */
public class Boj2785 {
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] chain = new int[N];
		
		for(int i = 0; i < N; i++) {
			chain[i] = sc.nextInt();
		}
		
		Arrays.sort(chain);
		System.out.println(decompressed(N, chain));
	}
	
	private static int decompressed(int n, int[] arr) {
		int disarm = 0;
		int subset = 0;
		
		for(int i = 0; i < n; i++) {
			disarm = n - 1 - i;		// 해제되지 않은 체인 개수
			subset += arr[i];		// 해제한 체인 개수
			
			if(subset >= disarm) break;
		}
		
		return disarm;
	}
}
