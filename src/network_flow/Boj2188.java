package network_flow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 	
 * 	@author minchoba
 *	백준 2188번: 축사 배정
 *
 *	@see https://www.acmicpc.net/problem/2188/
 *
 */
public class Boj2188 {
	private static int[] shed;
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
		
		for(int cow = 0; cow < N; cow++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			
			while(count-- > 0) {
				int shed = Integer.parseInt(st.nextToken());
				connected[cow].add(shed - 1);				// 축사 연결
			}
		}
		
		System.out.println(bipartiteMatch(N, M));
	}
	
	private static int bipartiteMatch(int n, int m) {
		shed = new int[m];
		Arrays.fill(shed, -1);
		
		int assign = 0;
		for(int start = 0; start < n; start++) {
			boolean[] visit = new boolean[n];
			if(dfs(m, visit, start)) assign++;		// 배정된 수++
 		}
		
		return assign;
	}
	
	private static boolean dfs(int m, boolean[] visit, int current) {
		if(visit[current]) return false;
		visit[current] = true;
		
		for(int next: connected[current]) {
			if(shed[next] == -1 || dfs(m, visit, shed[next])) {		// 빈 축사이거나 소를 다른 축사로 옮길 수 있는 경우
				shed[next] = current;
				
				return true;
			}
		}
		
		return false;
	}
}
