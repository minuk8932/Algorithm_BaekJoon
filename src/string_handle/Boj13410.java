package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13410번: 거꾸로 구구단
 *
 *	@see https://www.acmicpc.net/problem/13410/
 *
 */
public class Boj13410 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		System.out.println(max(N, K));
	}
	
	private static int max(int n, int k) {
		String[] nums = new String[k];
		
		for(int i = 0; i < k; i++) {
			nums[i] = ((i + 1) * n) + "";
		}
		
		char[][] arr = new char[k][];
		int[] res = new int[k];
		
		for(int i = 0; i < k; i++) {
			arr[i] = nums[i].toCharArray();
		}

		for(int i = 0; i < k; i++) {
			for(int j = 0; j < arr[i].length; j++) {					// 자리 바꾸기
				res[i] += ((arr[i][j] - '0') * (int) Math.pow(10, j));
			}
		}
		
		int max = 0;
		for(int i = 0; i < k; i++) {
			if(max < res[i]) max = res[i];
		}
		
		return max;
	}
}
