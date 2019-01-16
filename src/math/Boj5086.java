package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5086번: 배수와 약수
 *
 *	@see https://www.acmicpc.net/problem/5086/
 *
 */
public class Boj5086 {
	private static final String TERMINATE = "0 0";
	private static final String NEW_LINE = "\n";
	
	private static String F = "factor";
	private static String M = "multiple";
	private static String N = "neither";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			if(TERMINATE.equals(line)) break;
			
			StringTokenizer st = new StringTokenizer(line);
			sb.append(getResult(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static String getResult(int a, int b) {
		if(a % b == 0) return M;
		else if(b % a == 0) return F;
		else return N;
	}
}
