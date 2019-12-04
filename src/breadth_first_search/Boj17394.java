package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17394번: 핑거 스냅
 *
 *	@see https://www.acmicpc.net/problem/17394/
 *
 */
public class Boj17394 {
	private static final int INF = 1_000_001;
	private static final String NEW_LINE = "\n";
	private static final int[] FORMULA = {3, 2, -1, 1};
	
	private static boolean[] prime = new boolean[INF];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		eratosthenesSieve();
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			sb.append(search(N, A, B)).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static void eratosthenesSieve() {				// find prime
		Arrays.fill(prime, true);
		prime[0] = prime[1] = false;
		
		for(int i = 2; i < 1_001; i++) {
			if(!prime[i]) continue;
			
			for(int j = i + i; j < INF; j += i) {
				prime[j] = false;
			}
		}
	}
	
	private static int search(int n, int a, int b) {
		if((n == a && prime[a]) || (n == b && prime[b])) return 0;
		
		int[] visit = new int[INF];
		Arrays.fill(visit, INF);
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(n);
		
		visit[n] = 1;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(final int F: FORMULA) {										// snapping
				int next = F == 1 || F == -1 ? current + F: current / F;
				
				if(next < 0 || next >= INF) continue;
				if(visit[next] > visit[current] + 1) {
					visit[next] = visit[current] + 1;
					
					q.offer(next);
				}
			}
		}
		
		int min = INF;
		
		for(int i = a; i <= b; i++) {
			if(visit[i] != 0 && prime[i]) {									// find minimum
				if(visit[i] < min) min = visit[i];
			}
		}
		
		
		return min == INF ? -1: min - 1;
	}
}
