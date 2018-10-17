package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12871번: 무한 문자열
 *
 *	@see https://www.acmicpc.net/problem/12871/
 *
 */
public class Boj12871 {	
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String t = br.readLine();
		
		int sLeng = s.length();
		int tLeng = t.length();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < sLeng; i++) {			// 문자열 t를 s의 길이 횟수 만큼 이어 붙임
			sb.append(t);
		}
		
		sb.append(SPACE);							// 중간에 공백 끼워넣기

		for(int i = 0; i < tLeng; i++) {			// 문자열 s를 t의 길이 횟수 만큼 이어 붙임
			sb.append(s);
		}
		
		StringTokenizer st = new StringTokenizer(sb.toString());			// 두 문자열을 나누고
		
		System.out.println(st.nextToken().equals(st.nextToken()) ? 1 : 0);		// 서로 같은 문자열이면 1 아니면 0을 출력
	}
}
