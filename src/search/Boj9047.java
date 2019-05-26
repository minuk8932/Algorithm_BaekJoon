package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 9047번: 6174
 *
 *	@see https://www.acmicpc.net/problem/6174/
 *
 */
public class Boj9047 {
	private static final String NEW_LINE = "\n";
	private static final int TARGET = 6174;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int[] nums = new int[4];
			String input = br.readLine();
			
			int value = Integer.parseInt(input);
			
			for(int i = 0; i < 4; i++) {
				nums[i] = input.charAt(i) - '0';
			}
			
			sb.append(value == TARGET ? 0: search(nums)).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static int search(int[] arr) {
		int result = 0, count = 0;
		
		while(true) {
			Arrays.sort(arr);							// 배열 오름차순 정렬
			
			int src = 0, snk = 0;
			for(int i = 0; i < arr.length; i++) {
				src += arr[i] * (int) Math.pow(10, arr.length - 1 - i);		// 작은 수 만들기
			}
			
			for(int i = arr.length - 1; i >= 0; i--) {						// 큰 수 만들기
				snk += arr[i] * (int) Math.pow(10, i);
			}
			
			result = snk - src;
			count++;
			if(result == TARGET) break;				// 타겟과 같으면 종료
			
			for(int i = 0; i < arr.length; i++) {	// 아니면 새로 배열에 할당
				arr[i] = result % 10;
				result /= 10;
			}
		}
		
		return count;
	}
}
