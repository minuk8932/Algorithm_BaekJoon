package parsing;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10698번: Ahmed Aly
 *
 *	@see https://www.acmicpc.net/problem/10698/
 *
 */
public class Boj10698 {
	private static final char NEW_LINE = '\n';
	private static final String CASE = "Case ";
	private static final String COLON = ": ";
	private static final String Y = "YES";
	private static final String N = "NO";
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()) + 1;
		
		for(int i = 1; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			char O = st.nextToken().charAt(0);
			int B = Integer.parseInt(st.nextToken());
			char equal = st.nextToken().charAt(0);
			int C = Integer.parseInt(st.nextToken());
			
			sb.append(CASE).append(i).append(COLON).append(getFormat(A, O, B, C) ? Y: N).append(NEW_LINE);
		}
		
		System.out.println(sb);		// 결과 출력
	}
	
	private static boolean getFormat(int x, char o, int y, int z) {		// 파싱
		if(o == '+') return x + y == z ? true : false;
		else return x - y == z ? true : false;
	}
}
