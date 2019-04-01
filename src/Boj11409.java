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
 *	백준 11409번: 열혈강호 6
 *
 *	@see https://www.acmicpc.net/problem/11409/
 *
 */
public class Boj11409 {
	private static ArrayList<Integer>[] connected;
	private static int[][] flow;
	private static int[][] capacity;
	private static int[][] cost;
	private static int[] prev;
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int length = N + M + 2, src = N + M, snk = N + M + 1;
		
		connected = new ArrayList[length];
		flow = new int[length][length];
		capacity = new int[length][length];
		cost = new int[length][length];
		
		for(int i = 0; i < length; i++) {
			connected[i] = new ArrayList<>();
		}
		
		for(int emp = 0; emp < N; emp++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			
			capacity[src][emp] = 1;		// 소스와 직원 연결
			connected[src].add(emp);
			connected[emp].add(src);
			
			while(count-- > 0) {
				int work = Integer.parseInt(st.nextToken()) - 1 + N;
				int value = Integer.parseInt(st.nextToken());
				
				connected[emp].add(work);		// 작업과 직원 연결 및 비용 저장
				connected[work].add(emp);
				
				capacity[emp][work] = 1;
				cost[emp][work] = value;
				cost[work][emp] = -value;
			}
		}
		
		for(int work = N; work < M + N; work++) {	// 작업과 싱크 연결
			capacity[work][snk] = 1;
			connected[work].add(snk);
			connected[snk].add(work);
		}
		
		System.out.println(mcmf(src, snk, length));
	}
	
	private static StringBuilder mcmf(int source, int sink, int size) {
		StringBuilder sb = new StringBuilder();
		
		int result = 0, total = 0;
		prev = new int[size];
		
		while(true) {
			spfa(source, size);			// 최대 비용 경로를 통한 유량을 흘림
			
			if(prev[sink] == -1) break;
			
			for(int i = sink; i != source; i = prev[i]) {
				total += cost[prev[i]][i];					// 최대 비용
				flow[prev[i]][i] += 1;
				flow[i][prev[i]] -= 1;
			}
			
			result++;
		}
		
		return sb.append(result).append(NEW_LINE).append(total);
	}
	
	private static void spfa(int start, int size) {
		int[] dist = new int[size];
		boolean[] inQ = new boolean[size];
		
		Arrays.fill(prev, -1);
		Arrays.fill(dist, Integer.MIN_VALUE);
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		inQ[start] = true;
		dist[start] = 0;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			inQ[current] = false;
			
			for(int next: connected[current]) {
				// 유량 흘림 가능, 이전보다 더 많은 비용이 드는 경우
				if(dist[next] < dist[current] + cost[current][next] && capacity[current][next] - flow[current][next] > 0) {
					dist[next] = dist[current] + cost[current][next];
					prev[next] = current;
					
					if(!inQ[next]) {
						q.offer(next);
						inQ[next] = true;
					}
				}
			}
		}
	}
}
