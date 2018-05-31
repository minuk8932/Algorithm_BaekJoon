package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 12813번: 이진수 연산
 *
 *	@see https://www.acmicpc.net/problem/12813/
 *
 */
public class Boj12813 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] A = br.readLine().toCharArray();
		char[] B = br.readLine().toCharArray();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < A.length; i++) {			// and 연산
			boolean x = A[i] == '1' ? true : false;
			boolean y = B[i] == '1' ? true : false;
			
			sb.append(x & y == true ? 1 : 0);
		}
		
		sb.append(NEW_LINE);
		
		for(int i = 0; i < A.length; i++) {			// or 연산
			boolean x = A[i] == '1' ? true : false;
			boolean y = B[i] == '1' ? true : false;
			
			sb.append(x | y == true ? 1 : 0);
		}
		
		sb.append(NEW_LINE);
		
		for(int i = 0; i < A.length; i++) {			// xor 연산
			boolean x = A[i] == '1' ? true : false;
			boolean y = B[i] == '1' ? true : false;
			
			sb.append(x ^ y == true ? 1 : 0);
		}
		
		sb.append(NEW_LINE);
		
		for(int i = 0; i < A.length; i++) {			// !x 연산
			boolean x = A[i] == '1' ? true : false;
			
			sb.append(!x == true ? 1 : 0);
		}
		
		sb.append(NEW_LINE);
		
		for(int i = 0; i < A.length; i++) {			// !y 연산
			boolean y = B[i] == '1' ? true : false;
			
			sb.append(!y == true ? 1 : 0);
		}
		
		System.out.println(sb.toString());			//  결과값 한번에 출력
	}
}
