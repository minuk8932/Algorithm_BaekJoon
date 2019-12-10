package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 15668번: 방 번호
 *
 *	@see https://www.acmicpc.net/problem/15668/
 *
 */
public class Boj15668 {
	private static boolean[] used;
	
	private static final String ERROR = "-1";
	private static final String PLUS = " + ";
	
	private static final int INF = 100_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int N = Integer.parseInt(input);
		char[] n = input.toCharArray();
		
		System.out.println(makeAB(n, N));
	}
	
	private static String makeAB(char[] arr, int n) {
		StringBuilder sb = new StringBuilder();
		int loop = Math.min(n, INF);					// pigeon hole principle
		
		for(int i = 1; i < loop; i++) {
			int A = i, B = n - i;
			
			used = new boolean[10];
			if(!checkCipher(A)) continue;				// is unique
			if(!checkCipher(B)) continue;

	        return sb.append(A).append(PLUS).append(B).toString();
		}
		
		return ERROR;
	}
	
	private static boolean checkCipher(int src) {		
		while(src > 0) {
			int mod = src % 10;
			
			if(used[mod]) return false;
			used[mod] = true;
			
			src /= 10;
		}
		
		return true;
	}
}
