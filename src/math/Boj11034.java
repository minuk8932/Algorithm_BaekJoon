package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 11034번: 캥거루 세마리2
 *
 *	@see https://www.acmicpc.net/problem/11034/
 *
 */
public class Boj11034 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String input = "";
		while((input = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(input);
			
			int[] kangaroo = new int[3];
			for(int i = 0; i < 3; i++) {
				kangaroo[i] = Integer.parseInt(st.nextToken());
			}
			// 몇개 그려보면 답나옴
			sb.append(Math.max(kangaroo[1] - kangaroo[0], kangaroo[2] - kangaroo[1]) - 1).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
}