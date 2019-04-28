package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9469번: 폰 노이만
 *
 *	@see https://www.acmicpc.net/problem/9469/
 *
 */
public class Boj9469 {
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int P = Integer.parseInt(br.readLine());
		
		while(P-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			double D = Double.parseDouble(st.nextToken());
			double A = Double.parseDouble(st.nextToken());
			double B = Double.parseDouble(st.nextToken());
			double F = Double.parseDouble(st.nextToken());
			
			sb.append(N).append(SPACE).append(String.format("%.6f", D / (A + B) * F)).append(NEW_LINE);		// 시간당 각 이동거리를 구해 더한 값을 파리 속력에 곱
		}
		
		System.out.println(sb);
	}
}
