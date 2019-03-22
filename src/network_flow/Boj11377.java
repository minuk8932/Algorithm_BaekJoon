package network_flow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11377번: 열혈강호 3
 *
 *	@see https://www.acmicpc.net/problem/11377/
 *
 */
public class Boj11377 {
	private static ArrayList<Integer>[] connected;
	private static int[] work;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		connected = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			connected[i] = new ArrayList<>();
		}
		
		for(int emp = 0; emp < N; emp++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			
			while(count-- > 0) {
				int work = Integer.parseInt(st.nextToken());
				connected[emp].add(work - 1);
			}
		}
		
		System.out.println(bipartiteMatch(N, M, K));
	}
	
	private static int bipartiteMatch(int n, int m, int k) {
		int count = 0;
		
		work = new int[m];
		Arrays.fill(work, -1);
		
		for(int start = 0; start < n; start++) {		// 각자 1개의 작업씩 맡아 함.
			boolean[] visit = new boolean[n];
			
			if(dfs(m, visit, start)) count++;
		}
		
		for(int start = 0; start < n; start++) {		// 무작위로 남은 작업 할당
			if(k == 0) break;
			boolean[] visit = new boolean[n];
			
			if(dfs(m, visit, start)) count++;
			else continue;
			
			k--;
		}
		
		return count;
	}
	
	private static boolean dfs(int m, boolean[] visit, int current) {
		if(visit[current]) return false;
		visit[current] = true;
		
		for(int next: connected[current]) {
			if(work[next] == -1 || dfs(m, visit, work[next])) {		// 할당 가능한 작업이 있으면 할당
				work[next] = current;
				 return true;
			}
		}
		
		return false;
	}
}
