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
 *	백준 1671번: 상어의 저녁식사
 *
 *	@see https://www.acmicpc.net/problem/1671/
 *
 */
public class Boj1671 {
	private static ArrayList<Integer>[] connected;
	private static int[][] capacity;
	private static int[][] flow;
	private static int[] prev;
	
	private static class Shark{
		int size;
		int fast;
		int head;
		
		public Shark (int size, int fast, int head) {
			this.size = size;
			this.fast = fast;
			this.head = head;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int leng = N * 2 + 2;
		
		connected = new ArrayList[leng];
		capacity = new int[leng][leng];
		flow = new int[leng][leng];
		
		Shark[] shrk = new Shark[N];
		
		int src = N * 2, snk = N * 2 + 1;
		
		for(int i = 0; i < leng; i++) {
			connected[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < leng - 2; i++) {
			if(i < N) {						// src부터 각 상어는 최대 유량 2
				connected[src].add(i);
				connected[i].add(src);
				capacity[src][i] = 2;
			}
			
			else {							// 상어가 한번 먹히면 그 다음엔 유량이 없으니 최대 1
				connected[i].add(snk);
				connected[snk].add(i);
				capacity[i][snk] = 1;
			}
		}
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			shrk[i] = new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i == j) continue;
				
				int another = j + N;
				
				if(shrk[i].fast >= shrk[j].fast && shrk[i].head >= shrk[j].head && shrk[i].size >= shrk[j].size) {
					if(shrk[i].fast == shrk[j].fast && shrk[i].head == shrk[j].head && shrk[i].size == shrk[j].size) {
						if(i < j) {						// 능력치가 같으면 누가 먹을건지 구분
							capacity[i][another] = 1;
							connected[i].add(another);
							connected[another].add(i);
						}
					}
					else {								// 다른 경우엔 큰 상어가 잡아먹는다
						capacity[i][another] = 1;
						connected[i].add(another);
						connected[another].add(i);
					}
				}
			}
		}
		
		System.out.println(networkFlow(N, leng, src, snk));
	}
	
	private static int networkFlow(int n, int size, int source, int sink) {
		int result = 0;
		
		prev = new int[size];
		
		while(true) {
			Arrays.fill(prev, -1);
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(source);
			
			while(!q.isEmpty() && prev[sink] == -1) {
				int current = q.poll();
				
				for(int next: connected[current]) {
					if(prev[next] != -1) continue;

					if(capacity[current][next] - flow[current][next] > 0) {		// 유량이 0 -> 잡아 먹힌 상어
						prev[next] = current;
						q.offer(next);
					}
				}
			}
			
			if(prev[sink] == -1) break;
			
			for(int i = sink; i != source; i = prev[i]) {
				flow[prev[i]][i]++;
				flow[i][prev[i]]--;
			}
			
			result++;
		}
		
		return n - result;		// 유량: 잡아먹힌상어, 따라서 생존수를 반환
	}
}
