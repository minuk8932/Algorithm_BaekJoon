package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 17285번: XORChic
 *
 *	@see https://www.acmicpc.net/problem/17285/
 *
 */
public class Boj17285 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		System.out.println(getPlainText(br.readLine().toCharArray()));
	}
	
	private static String getPlainText(char[] words) {
		StringBuilder sb = new StringBuilder();
		char[] pw = Integer.toBinaryString(words[0]).toCharArray();
		char[] target = Integer.toBinaryString(67).toCharArray();
		
		int key = 0;
		
		for(int i = 6; i >= 0; i--) {
			if(xor(pw, target, i)) {			// xor 성질을 통해 key 계산
				key += Math.pow(2, 6 - i); 
			}
		}
		
		for(int i = 0; i < words.length; i++) {		// 각 자리별 다시 xor
			sb.append((char) (key ^ words[i]));
		}
		
		return sb.toString();
	}
	
	private static boolean xor(char[] pArr, char[] tArr, int idx) {
		char p = ' ';
		char t = ' ';
		
		if(pArr.length <= idx) {
			p = '0';
		}
		else if(tArr.length <= idx) {
			t = '0';
		}
		else {
			p = pArr[idx];
			t = tArr[idx];
		}
		
		return p != t ? true: false;
	}
}
