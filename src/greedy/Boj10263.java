package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10263번: 개회식
 *
 *	@see https://www.acmicpc.net/problem/10263/
 *
 */
public class Boj10263 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] tower = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i < n + 1; i++) {
			tower[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(cannonShot(n, tower));
	}
	
	private static int cannonShot(int n, int[] arr) {
		Arrays.sort(arr);
		int target = arr[n];
		
		for(int i = n - 1; i >= 0; i--) {				// 싹다 칠지, 하나만 통으로 칠지
			target = Math.min(target, arr[i] + n - i);
		}
		
		return target;
	}
}
