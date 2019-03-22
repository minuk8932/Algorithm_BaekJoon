package network_flow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11376번: 열혈강호 2
 *
 *	@see https://www.acmicpc.net/problem/11376/
 *
 */
public class Boj11376 {
	private static int[] work;
	private static ArrayList<Integer>[] connected;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
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
		
		System.out.println(bipartiteMatch(N, M));
	}
	
	private static int bipartiteMatch(int n, int m) {
		work = new int[m];
		Arrays.fill(work , -1);
		
		int count = 0;
		
		for(int start = 0; start < n; start++) {
			int[] visit = new int[n];
			
			if(dfs(n, m, visit, start)) count++;		// 인당 2회씩 호출
			if(dfs(n, m, visit, start)) count++;
		}
		
		return count;
	}
	
	private static boolean dfs(int n, int m, int[] visit, int current) {
		if(visit[current] >= 2) return false;			// 이미 두개의 작업을 처리중인 사람
		visit[current]++;
		
		for(int next: connected[current]) {			
			if(work[next] == -1 || dfs(n, m, visit, work[next])) {
				work[next] = current;					// 아직 배치되지 않은 작업 연결
				
				return true;
			}
		}
		
		return false;
	}
}
