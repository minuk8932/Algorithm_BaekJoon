package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 2485번: 가로수
 *
 *	@see https://www.acmicpc.net/problem/2485/
 *
 */
public class Boj2485 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] pos = new int[N];
		for(int i = 0; i < N; i++) {
			pos[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(getTreeCount(N, pos));
	}
	
	private static long getTreeCount(int n, int[] arr) {
		int[] diff = getDiff(arr);	// 각 나무간 거리 차이
		int gcd = getGcd(diff);		// 최대 공약수 구하기
		
		return getTotal(n, diff, gcd);
	}
	
	private static int[] getDiff(int[] arr) {
		int[] d = new int[arr.length];
		d[0] = -1;
		
		for(int i = 1; i < arr.length; i++) {			
			d[i] = Math.abs(arr[i] - arr[i - 1]);
		}
		
		return d;
	}
	
	private static long getTotal(int n, int[] diff, int gcd) {
		long total = 0;
		
		for(int i = 1; i < n; i++) {
			int trees = diff[i] / gcd;		
			total += (trees - 1);
		}
		
		return total;
	}
	
	private static int getGcd(int[] diff) {
		Arrays.sort(diff);
		int mod = diff[1];
		
		for(int i = 1; i < diff.length; i++) {
			boolean loop = diff[i] % mod == 0 ? true : false;
			
			if(!loop) {
				i = 0;
				mod--;
			}
		}
		
		return mod;
	}
}
