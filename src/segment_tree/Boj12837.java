package segment_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12837번: 가계부 (Hard)
 *
 *	@see https://www.acmicpc.net/problem/12837/
 *
 */
public class Boj12837 {
	private static final String NEW_LINE = "\n";
	
	private static long[] tree;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int S = 1;
		while(S <= N) S <<= 1;
		
		tree = new long[S * 2];
		StringBuilder sb = new StringBuilder();
		
		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			
			if(cmd == 1) {
				int day = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				input(day + S - 1, cost);					// 해당 날짜에 변동 금액 추가
			}
			else {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				sb.append(sum(from + S - 1, to + S - 1)).append(NEW_LINE);		// 기간 내 변동 금액
			}
		}
		
		System.out.print(sb);
	}
	
	private static void input(int index, int value) {		
		while(index > 0) {
			tree[index] += value;
			index /= 2;
		}
	}
	
	private static long sum(int from, int to) {
		long total = 0;

		while(from <= to) {
			if(from % 2 == 1) total += tree[from++];			
			if(to % 2 == 0) total += tree[to--];
			
			from /= 2;
			to /= 2;
		}
		
		return total;
	}
}
