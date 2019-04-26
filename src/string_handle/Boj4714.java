package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4714번: Lunacy
 *
 *	@see https://www.acmicpc.net/problem/4714/
 *
 */
public class Boj4714 {
	private static final double PERMUTATION = 0.167;
	private static String[] FORM = {"Objects weighing ", " on Earth will weigh ", " on the moon.\n"};
	private static String DOT = ".";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			double weight = Double.parseDouble(br.readLine());
			if(weight < 0) break;
			
			String prev = weight + "";
			String post = String.format("%.3f", weight * PERMUTATION);				// 결과값
			post = post.substring(0, post.length() - 1);
			
			int idx = prev.indexOf(DOT);
			if(idx > prev.length() - 3) prev = String.format("%.2f", weight);		// 출력도 최소 소숫점 2자리까지
			
			sb.append(FORM[0]).append(prev).append(FORM[1]).append(post).append(FORM[2]);
		}
		
		System.out.println(sb);
	}
}
