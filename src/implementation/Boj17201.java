package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 17201번: 자석 체인
 *
 *	@see https://www.acmicpc.net/problem/17201/
 *
 */
public class Boj17201 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		System.out.println(isLinked(n, br.readLine()));
	}
	
	private static String isLinked(int n, String str) {
		int[] magnet = new int[n];
		
		for(int i = 0; i < n; i++) {
			int index = i * 2;
			magnet[i] = (str.charAt(index) - '0') * 10 + (str.charAt(index + 1) - '0');
			
			if(i < 1) continue;
			if(magnet[i - 1] % 10 == magnet[i] / 10) return "No";		// 같은 극이면 No
		}
		
		return "Yes";
	}
}
