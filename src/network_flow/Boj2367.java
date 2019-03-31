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
 *	백준 2367번: 파티
 *
 *	@see https://www.acmicpc.net/problem/2367/
 *
 */
public class Boj2367 {
	private static ArrayList<Integer>[] connected;
	
	private static int[][] flow;
	private static int[][] capacity;
	private static int[] prev;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int length = N + D + 2;
		
		connected = new ArrayList[length];
		flow = new int[length][length];
		capacity = new int[length][length];
		
		for(int i = 0; i < N + D + 2; i++) {
			connected[i] = new ArrayList<>();
		}
		
		int source = N + D, sink = N + D + 1;
		
		for(int i = 0; i < N; i++) {		// 소스에서 사람 연결
			connected[source].add(i);
			connected[i].add(source);
			
			capacity[source][i] = K;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = N; i < N + D; i++) {			// 음식에서 싱크 연결
			connected[i].add(sink);
			connected[sink].add(i);
			
			capacity[i][sink] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			
			while(count-- > 0) {
				int num = Integer.parseInt(st.nextToken()) - 1 + N;
				
				connected[i].add(num);
				connected[num].add(i);
				
				capacity[i][num] = 1;
			}
		}
		
		System.out.println(networkFlow(N, source, sink, length));
	}
	
	private static int networkFlow(int n, int S, int T, int size) {
		int result = 0;
		
		while(true) {
			prev = new int[size];
			Arrays.fill(prev, -1);
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(S);
			
			while(!q.isEmpty() && prev[T] == -1) {
				int current = q.poll();
				
				for(int next: connected[current]) {
					if(prev[next] != -1) continue;
					
					if(capacity[current][next] - flow[current][next] > 0) {		// 유량 이동 가능시
						prev[next] = current;
						
						q.offer(next);
					}
				}
			}
			
			if(prev[T] == -1) break;
			
			for(int i = T; i != S; i = prev[i]) {		// 사람과 음식 사이는 무조건 유량 1
				flow[prev[i]][i]++;
				flow[i][prev[i]]--;
			}
			
			result++;
		}
		
		return result;
	}
}
