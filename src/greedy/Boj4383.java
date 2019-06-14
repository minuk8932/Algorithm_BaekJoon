package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 4383번: 점프는 즐거워
 *
 *	@see https://www.acmicpc.net/problem/4383/
 *
 */
public class Boj4383 {
	private static final String TRUE = "Jolly\n";
	private static final String FALSE = "Not jolly\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String line = "";
		while((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			int N = Integer.parseInt(st.nextToken());
			
			int[] seq = new int[N];
			for(int i = 0; i < N; i++) {
				seq[i] = Integer.parseInt(st.nextToken());
			}
			
			sb.append(jumpJolly(seq));
		}
		
		System.out.println(sb.toString());
	}
	
	private static String jumpJolly(int[] arr) {
		boolean[] check = new boolean[arr.length];
		
		for(int i = 0; i < arr.length - 1; i++) {
			int diff = Math.abs(arr[i] - arr[i + 1]);
			
			if(diff >= check.length) return FALSE;		// 범위를 벗어나는 값 -> 하나라도 벗어나면 (1 ~ n - 1)까지 수가 하나 빠진다. 
			check[diff] = true;							// 범위 내 숫자 등장
		}
		
		return isJolly(check) ? TRUE: FALSE;
	}
	
	private static boolean isJolly(boolean[] arr) {
		for(int i = 1; i < arr.length; i++) {			// 점프 졸리인가?
			if(!arr[i]) return false;
		}
		
		return true;
	}
}
