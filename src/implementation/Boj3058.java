package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3058번: 짝수를 찾아라
 *
 *	@see https://www.acmicpc.net/problem/3058/
 *
 */
public class Boj3058 {
	private static final char NEW_LINE = '\n';
	private static final char SPACE = ' ';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int min = 101;
			int sum = 0;
			
			while(st.hasMoreTokens()) {
				int num = Integer.parseInt(st.nextToken());
				
				if(num % 2 == 0) {
					sum += num;
					
					if(min > num) min = num;
				}
			}
			
			sb.append(sum).append(SPACE).append(min).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
}
