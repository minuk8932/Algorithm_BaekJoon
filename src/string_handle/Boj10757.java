package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10757: 큰 수 A + B
 *
 *	@see https://www.acmicpc.net/problem/10757/
 *
 */
public class Boj10757 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String n1 = st.nextToken();
		String n2 = st.nextToken();
		
		calculator(n1, n2);
	}
	
	private static void calculator(String A, String B) {
		int leng1 = A.length();
		int leng2 = B.length();
		int leng = 0;
		
		char[] num1 = null;
		char[] num2 = null;
		
		if(leng1 > leng2) {				// 길이가 짧은 것이 있다면 앞에 짧은 만큼 0을 붙여줌
			num1 = A.toCharArray();
			leng = leng1;
			
			num2 = stringToCharArr(B, leng2, leng1);
		}
		else {
			num2 = B.toCharArray();
			leng = leng2;
			
			num1 = stringToCharArr(A, leng1, leng2);
		}
		
		System.out.println(makeBigInteger(leng, num1, num2));
	}
	
	private static char[] stringToCharArr(String str, int leng1, int leng2) {
		char[] arr = new char[leng2];
		
		int idx = 0;
		for(int i = 0; i < leng2; i++) {
			if(i < leng2 - leng1) {
				arr[i] = '0';
			}
			else {
				arr[i] = str.charAt(idx++);
			}
		}
		
		return arr;
	}
	
	private static StringBuilder makeBigInteger(int length, char[] arr1, char[] arr2) {
		Stack<Integer> res = new Stack<>();
		int carry = 0;
		
		for(int i = length - 1; i >= 0; i--) {
			int tmp = (arr1[i] - '0') + (arr2[i] - '0') + carry;
			
			if(tmp > 9) {
				tmp -= 10;
				res.push(tmp);
				
				carry = 1;
			}
			else {
				res.push(tmp);
				carry = 0;
			}
		}
		
		if(carry == 1) res.push(1);				// 마지막에 carry가 존재 할 경우
		
		StringBuilder sb = new StringBuilder();
		while(!res.isEmpty()) sb.append(res.pop());
		
		return sb;
	}
}
