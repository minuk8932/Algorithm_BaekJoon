package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9300번: Where's the Rainbow
 *
 *	@see https://www.acmicpc.net/problem/9300/
 *
 */
public class Boj9300 {
	private static final String CASE = "Case ";
	private static final String COLON = ": ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double h = Double.parseDouble(st.nextToken());
			double radian = Math.toRadians(Double.parseDouble(st.nextToken()));
			
			String sin = String.format("%.8f", Math.sin(radian));		// 6자리까지 오차 가능 -> 8자리까진 늘려줘야 6자리가 정확하게 나옴
			String cos = String.format("%.8f", Math.cos(radian));
			
			double sine = Double.parseDouble(sin);
			double cosine = Double.parseDouble(cos);
																				// 무지개까지 거리 d를 tan 계산
			sb.append(CASE).append(t + 1).append(COLON).append(Math.abs(h / (sine / cosine) - h)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
}
