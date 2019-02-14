package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2477번: 참외밭
 *
 *	@see https://www.acmicpc.net/problem/2477/
 *
 */
public class Boj2477 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		int[] melon = new int[6];
		int[] max = new int[2]; 	// 0: 1,2 max / 1: 1,2
		
		for(int i = 0; i < 6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int compass = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			
			melon[i] = length;
			
			if(i % 2 == 0) max[0] = Math.max(max[0], melon[i]);
			else max[1] = Math.max(max[1], melon[i]);
		}
		
		int[] digged = digPart(melon, max);				// 최대가 아닌 길이 중 파여있는 길이
		System.out.println(getCount(max, digged, K));
	}
	
	private static int[] digPart(int[] m, int[] max) {
		int[] arr = new int[2];
		
		for(int i = 0; i < 6; i++) {
			if (i % 2 == 0) {
	            if (max[1] == m[(i + 5) % 6] + m[(i + 1) % 6]) {	// 부분합이 가장 긴 것과 동일한 경우 -> 파임
	                arr[0] = m[i];
	            }
	        } 
			else {
	            if (max[0] == m[(i + 5) % 6] + m[(i + 1) % 6]) {
	                arr[1] = m[i];
	            }
	        }
		}
		
		return arr;
	}
	
	private static int getCount(int[] arr, int[] dig, int count) {
		return ((arr[0] * arr[1]) - (dig[0] * dig[1])) * count;
	}
}
