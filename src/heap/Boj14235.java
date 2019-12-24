package heap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 14235번: 크리스마스 선물
 *
 *	@see https://www.acmicpc.net/problem/14235/
 *
 */
public class Boj14235 {
	private static final String NEW_LINE = "\n";
	private static final String NOTHING = "0";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		int count = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		while(n-- > 0) {
			String line = br.readLine();
			
			if(line.equals(NOTHING)) {
				if(count == 0) {
					sb.append(-1).append(NEW_LINE);
					continue;
				}
				
				count--;
				sb.append(-pq.poll()).append(NEW_LINE);					// give gift
			}
			else {				
				StringTokenizer st = new StringTokenizer(line);			// base
				count += Integer.parseInt(st.nextToken());
				
				while(st.hasMoreTokens()) {	
					pq.offer(-Integer.parseInt(st.nextToken()));
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}
