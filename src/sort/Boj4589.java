package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 4589번: Gnome Sequencing
 *
 *	@see https://www.acmicpc.net/problem/4589/
 *
 */
public class Boj4589 {
	private static final String TITLE = "Gnomes:";
	private static final String O = "Ordered"; 
	private static final String X = "Unordered";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		sb.append(TITLE).append(NEW_LINE);
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			boolean flag = a < b;
			
			if(flag) sb.append(b < c ? O: X).append(NEW_LINE);
			else sb.append(b > c ? O: X).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}
