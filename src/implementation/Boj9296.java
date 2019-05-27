package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 9296번: Grading Exam
 *
 *	@see https://www.acmicpc.net/problem/9269/
 *
 */
public class Boj9296 {
	private static final String NEW_LINE = "\n";
	private static final String CASE = "Case ";
	private static final String COLON = ": ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			int L = Integer.parseInt(br.readLine());
			char[] ans = br.readLine().toCharArray();
			char[] rep = br.readLine().toCharArray();
			
			sb.append(CASE).append(t + 1).append(COLON).append(compare(L, ans, rep)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int compare(int leng, char[] arr1, char[] arr2) {
		int count = 0;
		
		for(int i = 0; i < leng; i++) {
			if(arr1[i] != arr2[i]) count++;
		}
		
		return count;
	}
}
