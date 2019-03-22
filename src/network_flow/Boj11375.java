package network_flow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11375번: 열혈 강호
 *
 *	@see https://www.acmicpc.net/problem/11375/
 *
 */
public class Boj11375 {
	private static boolean[][] connected;
	private static int[] emp, work;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		connected = new boolean[N][M];
		
		for(int emp = 0; emp < N; emp++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			
			while(count-- > 0) {
				int work = Integer.parseInt(st.nextToken()) - 1;
				connected[emp][work] = true;						// 작업과 직원간 연결 상태
			}
		}
		
		System.out.println(bipartiteMatch(N, M));
	}
	
	private static int bipartiteMatch(int n, int m) {
		int count = 0;
		emp = new int[n];			// 직원
		Arrays.fill(emp, -1);
		
		work = new int[m];			// 작업
		Arrays.fill(work, -1);
		
		int[] visit = new int[n];
		int visitCnt = 0;
		
		for(int start = 0; start < n; start++) {
			visitCnt++;
			if(dfs(n, m, visit, start, visitCnt)) count++;
		}
		
		return count;
	}
	
	private static boolean dfs(int n, int m, int[] visit, int current, int count) {
		if(visit[current] == count) return false;				// 이미 해당 직원이 작업을 잡고있는 경우
		visit[current] = count;
		
		for(int next = 0; next < m; next++) {
			if(!connected[current][next]) continue;
			
			if(work[next] == -1 || dfs(n, m, visit, work[next], count)) {		// 목표 작업이 아직 할당되지 않았거나, 다른 사람에게 할당 가능한 경우
				emp[current] = next;								// 현재 직원과 목표 작업 연결
				work[next] = current;
				
				return true;
			}
		}
		
		return false;
	}
}
