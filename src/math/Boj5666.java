package math;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

/**
 * 
 * 	@author exponential-e
 *	백준 5666번: 핫도그
 */
public class Boj5666 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String line = "";
		while((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			double H = Double.parseDouble(st.nextToken()) * 100;
			double P = Double.parseDouble(st.nextToken());
			
			sb.append(String.format("%.2f", (double) Math.round(H / P) / 100.0)).append(NEW_LINE);		// 반올림 소수 2째자리까지 표시
		}
		
		System.out.println(sb.toString());
	}
}
