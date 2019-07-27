package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 16244번: SpaceShip
 *
 *	@see https://www.acmicpc.net/problem/16244
 *
 */
public class Boj16244 {
	private static final char SPACE = ' ';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] enemy = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		long sum = 0;
		for(int i = 0; i < n; i++) {
			enemy[i] = Integer.parseInt(st.nextToken());
			sum += enemy[i];								// 2 * 마지막 처리할 우주선 = 총합
		}
		
		System.out.println(getSequence(n, enemy, sum / 2));
	}
	
	private static String getSequence(int n, int[] arr, long target) {
		StringBuilder sb = new StringBuilder();
		int flag = -1, idx = 0;
		
		while(idx < n) {
			if(flag == -1 && arr[idx] == target) flag = idx++;		// 마지막 처리 할 우주선 = 총합 / 2
			
			if(idx == n) break;
			sb.append(arr[idx++]).append(SPACE);
		}
		
		return sb.append(arr[flag]).toString();
	}
}
