package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author exponential-e
 * 백준 3486번: Adding Reversed Number
 * 
 * @see https://www.acmicpc.net/problem/3486/
 *
 */
public class Boj3486 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine());
			char[] A = st.nextToken().toCharArray();
			char[] B = st.nextToken().toCharArray();
			
			sb.append(sum(A, B)).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static int sum(char[] a, char[] b) {
		a = remake(a);
		b = remake(b);
		
		int val = getValue(a) +  getValue(b);
		
		StringBuilder result = new StringBuilder();
		while(val > 0) {
			result.append(val % 10);				// 변경 합을 뒤집어서 다시 저장
			val /= 10;
		}
		
		return Integer.parseInt(result.toString());
	}
	
	private static char[] remake(char[] arr) {
		for(int i = 0; i < arr.length / 2; i++) {		// 숫자 뒤집기
			char tmp = arr[i];
			arr[i] = arr[arr.length - 1 - i];
			arr[arr.length - 1 - i] = tmp;
		}
		
		return arr;
	}
	
	private static int getValue(char[] arr) {
		int value = 0;
		
		for(int i = 0; i < arr.length; i++) {			// 뒤집은 숫자를 수로 변경
			value *= 10;
			value += (arr[i] - '0');
		}
		
		return value;
	}
}
