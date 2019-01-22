package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 5217번: 쌍의 합
 *
 *	@see https://www.acmicpc.net/problem/5217/
 *
 */
public class Boj5217 {
	private static PriorityQueue<Pair> pq;
	
	private static final String P_FOR = "Pairs for ";
	private static final String COLON = ": ";
	private static final String COMMA = ", ";
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			pq = new PriorityQueue<>();
			dfs(n, 1);
			
			sb.append(P_FOR).append(n).append(COLON);
			int size = pq.size();
			
			while(size-- > 0) {
				Pair pair = pq.poll();
				sb.append(pair.x).append(SPACE).append(pair.y);
				
				if(pq.isEmpty()) break;			// 비었으면 콤마 안찍고 종료
				sb.append(COMMA);
			}
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static class Pair implements Comparable<Pair>{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			return this.x < p.x ? -1 : 1;
		}
	}
	
	private static void dfs(int target, int value) {
		if(target - value == value || target / 2 - value < 0) return;		// 기저 사례, 같은 값의 쌍이나 중복된 쌍이 존재하면 종료
		
		pq.offer(new Pair(value, target - value));
		dfs(target, value + 1);						// 재귀 호출
	}
}
