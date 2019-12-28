package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 18232번: 텔레포트 정거장
 *
 *	@see https://www.acmicpc.net/problem/18232/
 *
 */
public class Boj18232 {
	private static ArrayList<Integer>[] tele;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()) - 1;
		int E = Integer.parseInt(st.nextToken()) - 1;
		
		tele = new ArrayList[300_000];
		for(int i = 0; i < tele.length; i++) {
			tele[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int t1 = Integer.parseInt(st.nextToken()) - 1;
			int t2 = Integer.parseInt(st.nextToken()) - 1;
			
			tele[t1].add(t2);			// save teleport
			tele[t2].add(t1);
		}
		
		System.out.println(bfs(N, S, E));
	}
	
	private static int bfs(int n, int s, int e) {
		int[] visit = new int[tele.length];
		Arrays.fill(visit, 1_000_000_001);
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(s);
		
		visit[s] = 0;
		while(!q.isEmpty()) {
			int current = q.poll();
			
			int[] dir = new int[tele[current].size() + 2];
			for(int i = 0; i < dir.length - 2; i++) {			// find next with teleport
				dir[i] = tele[current].get(i);
			}
			
			dir[dir.length - 2] = current + 1;
			dir[dir.length - 1] = current - 1;
			
			for(int next: dir) {
				if(next < 0 || next >= n) continue;
				if(visit[next] > visit[current] + 1) {			// get minimum
					visit[next] = visit[current] + 1;
					
					q.offer(next);
				}
			}
		}
		
		return visit[e];
	}
}
