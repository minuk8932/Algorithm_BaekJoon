package network_flow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11102번: Party
 *
 *	@see https://www.acmicpc.net/problem/11102/
 *
 */
public class Boj11102 {
	private static final String NO_FLOW = "impossible";
	private static final String NEW_LINE = "\n";
	
	private static ArrayList<Integer>[] connected;
	private static int[][] flow;
	private static int[][] capacity;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		while(n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			
			int size = m + f + 2;
			connected = new ArrayList[size];
			flow = new int[size][size];
			capacity = new int[size][size];
			
			int src = m + f, snk = m + f + 1;
			
			for(int i = 0; i < size; i++) {
				connected[i] = new ArrayList<>();
			}
			
			for(int nurs = 0; nurs < f; nurs++) {
				connected[src].add(nurs);
				connected[nurs].add(src);
				
				st = new StringTokenizer(br.readLine());
				int count = Integer.parseInt(st.nextToken());
				
				while(count-- > 0) {
					int geek = Integer.parseInt(st.nextToken()) + f;
					
					connected[nurs].add(geek);
					connected[geek].add(nurs);
					capacity[nurs][geek] = 1;
					
					connected[snk].add(geek);
					connected[geek].add(snk);
					capacity[geek][snk] = 1;
				}
			}
			
			sb.append(isImpossible(f, m) ? NO_FLOW: networkFlow(size, src, snk, f)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static boolean isImpossible(int F, int M) {		// 파티를 몇번이나 해도 짝을 짓지못하는 geek이 존재하는 경우
		for(int i = F; i < M + F; i++) {
			boolean flag = false;
			
			for(int j = 0; j < F; j++) {				// 즉 nurse로부터 유량이 절대 흐르지 못함
				if(capacity[j][i] != 0) flag = true;
			}

			if(!flag) return true;
		}
		
		return false;
	}
	
	private static int networkFlow(int size, int S, int T, int F) {
		int[] prev = new int[size];
		int result = 0;
		
		while(true) {
			result++;
			for(int i = 0; i < F; i++) {			// 계속 유량을 흘려줌 (파티 지속) -> 짝을 찾을 때 까지
				capacity[S][i]++;
			}
			
			while(true) {
				Arrays.fill(prev, -1);
				
				Queue<Integer> q = new LinkedList<>();
				q.offer(S);
				
				while(!q.isEmpty() && prev[T] == -1) {
					int current = q.poll();
					
					for(int next: connected[current]) {
						if(prev[next] != -1) continue;					
						if(capacity[current][next] - flow[current][next] > 0) {
							prev[next] = current;
							
							q.offer(next);
						}
					}
				}
				
				if(prev[T] == -1) break;
				
				for(int i = T; i != S; i = prev[i]) {
					flow[prev[i]][i] += 1;
					flow[i][prev[i]] -= 1;
				}
			}
			
			boolean flag = false;
			
			for(int i = F; i < S; i++){				// 아직 유량이 채워지지 않은 곳이 있다면(커플이 되지못한 geek 존재), 파티를 더해야함
				if(flow[i][T] <= 0) flag = true;
            }
            
            if(!flag) break;
		}
		
		return result;
	}
}
