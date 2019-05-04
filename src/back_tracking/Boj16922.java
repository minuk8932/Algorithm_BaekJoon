package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 16922번: 로마 숫자 만들기
 *
 *	@see https://www.acmicpc.net/problem/16922/
 *
 */
public class Boj16922 {		
	private static int[] arr = {1, 5, 10, 50};
	private static boolean[] checker = new boolean[1_002];
	
	private static int result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		backTracking(N, 0, 0, 0);
		
		System.out.println(result);
	}
	
	private static void backTracking(int n, int count, int current, int value) {
		if(n == count) {
			if(!checker[value]) {			// 이미 만들어진 숫자는 갯수에 포함 안됨
				checker[value] = true;
				result++;
			}
			return;
		}
		
		for(int next = current; next < 4; next++) {
			backTracking(n, count + 1, next, value + arr[next]);
		}
	}
}
