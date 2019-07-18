package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 14395번: 4 연산
 *
 *	@see https://www.acmicpc.net/problem/14395/
 *
 */
public class Boj14395 {
	private static final char[] OPR = {'*', '+', '/'};
	private static final int INF = 1_000_000_001;
	
	private static class Pair{
		long x;
		String cost;
		
		public Pair(long x, String cost) {
			this.x = x;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		System.out.println(convert(s, t));
	}
	
	private static String convert(long src, long target) {
		HashSet<Long> set = new HashSet<>();
		
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(src, ""));
		
		while(!q.isEmpty()) {
			Pair current = q.poll();
			
			if(current.x == target) {
				if(!"".equals(current.cost)) return current.cost;		// 값 도달 시 연산 경로
				else return "0";										// 연산이 없는 경우
			}
			
			long pow = current.x * current.x;
			if(pow < INF){
	            if(!set.contains(pow)){
	                q.offer(new Pair(pow, current.cost + OPR[0]));		// 연산 경로가 long 범위를 벗어날 수 있다.
	                set.add(pow);
	            }
	        }
			
			long sum = current.x + current.x;
			if(sum < INF){
	            if(!set.contains(sum)){
	                q.offer(new Pair(sum, current.cost + OPR[1]));
	                set.add(sum);
	            }
	        }
			
			long div = current.x / current.x;
			if(div > 0){
	            if(!set.contains(div)){
	                q.offer(new Pair(div, current.cost + OPR[2]));
	                set.add(div);
	            }
	        }
		}
		
		return "-1";
	}
}
