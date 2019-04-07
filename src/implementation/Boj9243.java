package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 9243번: 파일 완전 삭제
 *
 *	@see https://www.acmicpc.net/problem/9243/
 *
 */
public class Boj9243 {
	private static final String AC = "Deletion succeeded";
	private static final String WA = "Deletion failed";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[] before = br.readLine().toCharArray();
		char[] after = br.readLine().toCharArray();
		
		System.out.println(delete(before, after, N % 2 == 0 ? false: true));
	}
	
	private static String delete(char[] b, char[] a, boolean flag) {
		for(int i = 0; i < a.length; i++) {
			boolean diff = ((b[i] - '0') ^ (a[i] - '0')) == 1 ? true: false;		// 서로 다른 값을 갖는 경우
			if(flag ^ diff) return WA;											// 덮어씌우기 횟수에 맞게 변경 되었는가
		}
		
		return AC;
	}
}
