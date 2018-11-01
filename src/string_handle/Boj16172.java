package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 16172번: 나는 친구가 적다 (Large)
 *
 *	@see https://www.acmicpc.net/problem/16172/
 *
 */
public class Boj16172 {
	private static int[] pi;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		for(char w: br.readLine().toCharArray()) {		// 숫자를 제외한 알파벳을 뽑아냄
			if(!(w <= '9' && '0' <= w)) sb.append(w);
		}
		
		String p = br.readLine();
		System.out.println(kmp(sb.toString(), p) ? 1 : 0);		// 원문과 비교할 문장을 kmp 메소드에 담고 참이면 1 거짓이면 0 출력
	}
	
	/**
	 * pi 배열 생성 메소드
	 * 
	 */
	private static void getPi(String p) {
		int m = p.length(), j = 0;
		
		pi = new int[m];
		for(int i = 1; i < m; i++) {
			while(j > 0 && p.charAt(i) != p.charAt(j)) {	// 동일한 문자열의 길이를 j로 가져옴, 없는 경우 돌지 않음
				j = pi[j - 1];
			}
				
			if(p.charAt(i) == p.charAt(j)) pi[i] = ++j;		// 두 문자의 값이 같다면 j + 1
		}
	}
	
	/**
	 * KMP 알고리즘 메소드
	 * 
	 */
	private static boolean kmp(String origin, String comp) {
		int j = 0;
		int n = origin.length(), m = comp.length();
		
		getPi(comp);		// pi를 가져오고
		
		for(int i = 0; i < n; i++) {
			while(j > 0 && origin.charAt(i) != comp.charAt(j)) {		// getPi 메소드와 같이 동작, 기존 문자열과 비교문자열을 통해 연산
				j = pi[j - 1];
			}
				
			if(origin.charAt(i) == comp.charAt(j)) {
				if(j == m - 1) return true;			// 기존 문자열을 비교문자열과 비교했을때 비교문자열 만큼 모두 동일한 경우 참
					
				j++;
			}
		}
		
		return false;			// 그 외 거짓
	}
}
