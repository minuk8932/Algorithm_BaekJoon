package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 * 	백준 14561번: 회문
 * 
 * 	@see https://www.acmicpc.net/problem/14561/
 *
 */
public class Boj14561 {
	private static final char NEW_LINE = '\n';
	private static final char C_TO_I = '0';
	private static final char NUMBER_LIMIT = '9';
	private static final char ALPHABET_A = 'A';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long A = Long.parseLong(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			LinkedList<Character> word  = nBasedA(A, n);
			sb.append(isPalindrome(word) ? 1 : 0).append(NEW_LINE);
		}

		System.out.println(sb);		// 결과 출력
	}
	
	private static LinkedList<Character> nBasedA(long num, int base) {
		LinkedList<Character> str = new LinkedList<>();
		
		while(num > 0) {
			char w = (char)((num % base) + C_TO_I);
			
			if(w > NUMBER_LIMIT) {										// w가 9보다 큰 경우 A ~ F(16진수까지)로 바꾸어줌
				w = (char) ((w - NUMBER_LIMIT) + ALPHABET_A - 1);
			}
			
            str.addFirst(w);
			num /= base;
		}
		
		return str;
	}
	
	private static boolean isPalindrome(LinkedList<Character> num) {
		int leng = num.size() / 2;
		
		for(int i = 0; i < leng; i++) {
			if(num.removeFirst() != num.removeLast()) return false;
		}
		
		return true;
	}
}
