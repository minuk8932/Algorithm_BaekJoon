package network_flow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1298번: 노트북의 주인을 찾아서
 *
 *	@see https://www.acmicpc.net/problem/1298/
 *
 */
public class Boj1298 {	
	private static ArrayList<Integer>[] connected;
	private static int[] note;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		connected = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			connected[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			connected[Integer.parseInt(st.nextToken()) - 1].add(Integer.parseInt(st.nextToken()) - 1);
		}
		
		System.out.println(bipartiteMatch(N));
	}
	
	private static int bipartiteMatch(int n) {
		int count = 0;
		
		note = new int[n];
		Arrays.fill(note, -1);
		
		for(int start = 0; start < n; start++) {
			boolean[] visit = new boolean[n];
			if(dfs(visit, start)) count++;
		}
		
		return count;
	}
	
	private static boolean dfs(boolean[] visit, int current) {
		if(visit[current]) return false;
		visit[current] = true;
		
		for(int next: connected[current]) {
			if(note[next] == -1 || dfs(visit, note[next])) {		// 주인 없는 노트북 주인 찾아주기
				note[next] = current;
				return true;
			}
		}
		
		return false;
	}
}
