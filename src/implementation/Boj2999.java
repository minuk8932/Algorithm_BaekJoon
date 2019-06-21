package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2999번: 비밀 이메일
 *
 *	@see https://www.acmicpc.net/problem/2999/
 *
 */
public class Boj2999 {
	private static int R, C;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String msg = br.readLine();
		getRC(msg);
		
		System.out.println(getMessage(msg.toCharArray()));
	}
	
	private static void getRC(String str) {
		int length = str.length();
		int sqrtLeng = (int) Math.sqrt(length) + 1;
		
		for(int i = sqrtLeng; i > 0; i--) {
			if(length % i == 0) {
				int div = length / i;
				R = Math.max(div, i);		// 열
				C = Math.min(div, i);		// 행
				break;
			}
		}
	}
	
	private static String getMessage(char[] word) {
		StringBuilder sb = new StringBuilder();
		
		for(int col = 0; col < C; col++) {
			for(int row = 0; row < R; row++) {
				sb.append(word[row * C + col]);		// 배열 모양대로 담기
			}
		}
		
		return sb.toString();
	}
}
