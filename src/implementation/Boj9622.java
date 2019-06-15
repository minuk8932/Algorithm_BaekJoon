package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9622번: Cabin Baggage
 *
 *	@see https://www.acmicpc.net/problem/9622/
 *
 */
public class Boj9622 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		int total = 0;
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double length = Double.parseDouble(st.nextToken());
			double width = Double.parseDouble(st.nextToken());
			double depth = Double.parseDouble(st.nextToken());
			double weight = Double.parseDouble(st.nextToken());
			
			int allow = isAllowed(length, width, depth, weight);
			total += allow;
			sb.append(allow).append(NEW_LINE);
		}
		
		sb.append(total);
		System.out.println(sb.toString());
	}
	
	private static int isAllowed(double l, double w, double d, double kg) {					// 적재 가능 여부 판단
		if(((l <= 56 && w <= 45 && d <= 25) || (l + w + d) <= 125) && kg <= 7) return 1;
		else return 0;
	}
}
