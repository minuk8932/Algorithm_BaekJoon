package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15238번: Pirates
 *
 *	@see https://www.acmicpc.net/problem/15238/
 *
 */
public class Boj15238 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		
		int[] alphas = new int[26];
		for(char c: br.readLine().toCharArray()) {
			alphas[c - 'a']++;
		}
		
		getResult(alphas);
	}
	
	private static void getResult(int[] arr) {
		int max = 0, index = -1;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > max) {
				max = arr[i];
				index = i;
			}
		}
		
		System.out.println((char) (index + 'a') + " " + max);
	}
}
