package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 7523번: Gauß
 *
 *	@see https://www.acmicpc.net/problem/7523/
 *
 */
public class Boj7523 {
	private static final String SCENE = "Scenario #";
	private static final String COLON = ":";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
				
		for(int i = 1; i < T + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long from = Long.parseLong(st.nextToken());
			long to = Long.parseLong(st.nextToken());
			
			sb.append(SCENE).append(i).append(COLON).append(NEW_LINE).
				append(getSum(from, to)).append(NEW_LINE).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
	
	private static long getSum(long l, long r) {		// 범위내 총합 구하기
		return (l + r) * (r - l + 1) / 2;
	}
}
