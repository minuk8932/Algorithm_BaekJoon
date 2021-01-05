package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author exponential-e
 *	백준 12852번: 1로 만들기 2
 *
 *	@see https://www.acmicpc.net/problem/12852/
 *
 */
public class Boj12852 {
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	private static int[] visit;
	
	private static class Sequence{
		int val;
		String res;
		
		public Sequence(int val, String res) {
			this.val = val;
			this.res = res;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		search(N);
	}
	
	private static void search(int n) {
		if(n == 1){
			System.out.println(0 + "\n" + 1);
			return;
		}
		Queue<Sequence> q = new LinkedList<>();
		
		q.offer(new Sequence(n, "" + n));
		
		visit = new int[n + 1];
		visit[n] = 1;
		
		while(!q.isEmpty()) {
			Sequence current = q.poll();
			
			int[] dir = {current.val % 3 == 0 ? current.val / 3: -1, current.val % 2 == 0 ? current.val / 2 : -1, current.val - 1};		// make next
			for(int next: dir) {
				if(next == -1) continue;
				if(visit[next] != 0) continue;
				visit[next] = visit[current.val] + 1;
				
				if(next == 1) {													// get Shortest
					StringBuilder sb = new StringBuilder();
					sb.append(visit[next] - 1).append(NEW_LINE).append(current.res).append(SPACE).append(next);

					System.out.println(sb.toString());
					return;
				}
				
				q.offer(new Sequence(next, current.res + SPACE + next));
			}
		}
		
	}
}
