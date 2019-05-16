package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 17202번: 핸드폰 번호 궁합
 *
 *	@see https://www.acmicpc.net/problem/17202/
 *
 */
public class Boj17202 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		char[] num1 = br.readLine().toCharArray();
		char[] num2 = br.readLine().toCharArray();
		
		char[] numbers = new char[num1.length * 2];
		int idx = 0;
		
		for(int i = 0; i < numbers.length; i += 2) {
			numbers[i] = num1[idx];
			numbers[i + 1] = num2[idx++];
		}
		
		System.out.println(getHarmony(numbers));
	}
	
	private static StringBuilder getHarmony(char[] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int size = 16; size > 2; size--) {			
			for(int i = 1; i < size; i++) {
				int remake = ((arr[i - 1] - '0') + (arr[i] - '0')) % 10;		// 궁합 찾기
				arr[i - 1] = (char) (remake + '0');								// 찾은 궁합으로 갱신
			}
		}
		
		return sb.append(arr[0]).append(arr[1]);
	}
}
