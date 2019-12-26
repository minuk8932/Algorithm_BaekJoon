package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17834번: 사자와 토끼
 *
 *	@see https://www.acmicpc.net/problem/17834/
 *
 */
public class Boj17834 {
	private static int[] colored;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		colored = new int[N];
		ArrayList<Integer>[] path = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			path[from].add(to);
			path[to].add(from);
		}
		
		System.out.println(getCount(N, path));
	}
	
	private static long getCount(int n, ArrayList<Integer>[] list) {
		if(!bfs(n, list)) return 0;
		long[] result = new long[2];
		
		for(int i = 0; i < colored.length; i++) {			// counting
			result[colored[i] - 1]++;
		}
		
		return result[0] * result[1] * 2;
	}
	
	private static boolean bfs(int n, ArrayList<Integer>[] list) {		
		Queue<Integer> q = new LinkedList<>();
		q.offer(0);
		
		colored[0] = 1;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: list[current]) {									// coloring node
				if(colored[next] != 0) {
					if(colored[current] == colored[next]) return false;		// adj node is same
					continue;
				}
				
				colored[next] = colored[current] == 1 ? 2: 1;
				q.offer(next);
			}
		}
		
		return true;
	}
}
