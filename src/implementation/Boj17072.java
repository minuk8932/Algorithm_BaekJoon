package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17072번: 아스키 아트
 *
 *	@see https://www.acmicpc.net/problem/17072/
 *
 */
public class Boj17072 {
	private static final int[] RANGE = {510_000, 1_020_000, 1_530_000, 2_040_000};
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++) {
				sb.append(numberToASCII(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static char numberToASCII(int r, int g, int b) {
		int I = r * 2126 + g * 7152 + b * 722;
		
		if(I < RANGE[0]) return '#';
		else if(I >= RANGE[0] && I < RANGE[1]) return 'o';
		else if(I >= RANGE[1] && I < RANGE[2]) return '+';
		else if(I >= RANGE[2] && I < RANGE[3]) return '-';
		else return '.';
	}
}
