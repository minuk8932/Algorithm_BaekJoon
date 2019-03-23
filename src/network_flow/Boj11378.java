package network_flow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11378번: 열혈강호 4
 *
 *	@see https://www.acmicpc.net/problem/11378/
 *
 */
public class Boj11378 {
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
				int work = Integer.parseInt(st.nextToken()) - 1;
				connected[emp].add(work);
			}
		}
		
		System.out.println(bipartiteMatch(N, M, K));
	}
	
	private static int bipartiteMatch(int n, int m, int k) {
		int result = 0;
		work = new int[m];
		Arrays.fill(work, -1);
		
		boolean[] visit = new boolean[n];
		for(int i = 0; i < n; i++) {		// 1인 1작업 할당
			Arrays.fill(visit, false);
			if(dfs(visit, i)) result++;
		}
		
		while(k > 0) {						// 벌점 만큼 다시 돌리는데,
			int count = 0;
			
			for(int i = 0; i < n; i++) {
				if(k == 0) break;			// 벌점이 모두 소모된 경우 종료
				Arrays.fill(visit, false);
				
				if(!dfs(visit, i)) {
					count++;
					continue;
				}
				result++;
				k--;
			}
			
			if(count == n) break;			// 벌점이 아직 소모되지 않았는데 할당될 작업이 더 이상 없는 경우
		}

		return result;
	}
	
	private static boolean dfs(boolean[] visit, int current) {
		if(visit[current]) return false;
		visit[current] = true;
		
		for(int next: connected[current]) {
			if(work[next] == -1 || dfs(visit, work[next])) {		// 할당된 작업을 옮기거나, 할당되지 않은 작업을 할당함
				work[next] = current;
				return true;
			}
		}
		
		return false;
	}
}
