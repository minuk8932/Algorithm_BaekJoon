package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1259번: 팰린드롬 수
 *
 *	@see https://www.acmicpc.net/problem/1259/
 *
 */
public class Boj1259 {
	private static final String NEW_LINE = "\n";
	private static final String Y = "yes", N = "no";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			if(line.equals("0")) break;
			
			char[] nums = line.toCharArray();
			sb.append(isPalin(nums) ? Y : N).append(NEW_LINE);	// 팰린드롬 여부를 출력
		}
		
		System.out.println(sb);
	}
	
	private static boolean isPalin(char[] arr) {
		for(int i = 0; i < arr.length / 2; i++) {
			if(arr[i] != arr[arr.length - 1 - i]) return false;
		}
		
		return true;
	}
}
