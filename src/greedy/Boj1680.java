package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 	
 * 	@author exponential-e
 *	백준 1680번: 쓰레기 수거
 *
 *	@see https://www.acmicpc.net/problem/1680/
 *
 */
public class Boj1680 {
	private static final String NEW_LINE = "\n";
	
	private static class Spot{
		int idx;
		int val;
		
		public Spot(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			LinkedList<Spot> trash = new LinkedList<>();
			int loop = N;
			
			while(loop-- > 0) {
				st = new StringTokenizer(br.readLine());
				trash.add(new Spot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			sb.append(collect(W, trash)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static long collect(int limit, LinkedList<Spot> list) {
		int amount = 0;
		int move = 0;
		int prev = 0;
			
		while(!list.isEmpty()) {
			Spot current = list.removeFirst();
			
			amount += current.val;
			if(amount < limit) {						// continuous
				prev = current.idx;
				continue;
			}
			
			move += current.idx * 2;
			if(amount > limit) list.addFirst(current);	// overflow, revisit
			
			amount = 0;
		}

		return move + (amount != 0 ? prev * 2: 0);
	}
}
