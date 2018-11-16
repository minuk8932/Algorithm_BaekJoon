package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2447번: 별찍기 - 10
 *
 *	@see https://www.acmicpc.net/problem/2447/
 *
 */
public class Boj2447 {
	private static final char STAR = '*';
	private static final char SPACE = ' ';
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		process(Integer.parseInt(br.readLine()));
	}
	
	private static void process(int n) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int x = i, y = j;
				
				while(x > 0) {
					if(x % 3 == 1 && y % 3 == 1) break;
					
					x /= 3;
					y /= 3;
				}
				
				sb.append(x == 0 ? STAR : SPACE);		// 규칙에 맞추어 별을 찍는다.
			}
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
}
