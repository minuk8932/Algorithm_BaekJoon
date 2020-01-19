package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 18258번: 큐2
 *
 *	@see https://www.acmicpc.net/problem/18258/
 *
 */
public class Boj18258 {
	private static final String[] QUERY = {"push", "pop", "size", "empty", "front", "back"};
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		LinkedList<Integer> list = new LinkedList<>();
		int size = 0;
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String q = st.nextToken();
			
			if(q.equals(QUERY[0])) {
				int num = Integer.parseInt(st.nextToken());
				list.add(num);
				
				size++;
				continue;
			}
			
			if(q.equals(QUERY[1])) {
				if(size == 0) {
					sb.append(-1).append(NEW_LINE);
					continue;
				}
				
				size--;
				sb.append(list.remove()).append(NEW_LINE);
			}
			else if(q.equals(QUERY[2])) {
				sb.append(size).append(NEW_LINE);
			}
			else if(q.equals(QUERY[3])) {
				sb.append(size == 0 ? 1: 0).append(NEW_LINE);
			}
			else if(q.equals(QUERY[4])) {
				sb.append(size == 0 ? -1: list.peek()).append(NEW_LINE);
			}
			else {
				sb.append(size == 0 ? -1: list.peekLast()).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());
	}
}
