package network_flow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9577번: 토렌트
 *
 *	@see https://www.acmicpc.net/problem/9577/
 *
 */
public class Boj9577 {
	private static ArrayList<Integer>[] connected;
	private static int[] file;
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			file = new int[101];
			connected = new ArrayList[101];
			
			for(int i = 0; i < 101; i++) {
				connected[i] = new ArrayList<>();
			}
			
			for(int idx = 0; idx < m; idx++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				int count = Integer.parseInt(st.nextToken());
				
				while(count-- > 0) {
					int f = Integer.parseInt(st.nextToken());
					
					for(int i = start + 1; i < end + 1; i++) {
						connected[i].add(f);
					}
				}
			}
			
			sb.append(bipartiteMatch(n)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int bipartiteMatch(int N) {
		int count = 0;
		boolean[] visit;
		
		Arrays.fill(file, -1);
		
		for(int start = 1; start < 101; start++) {
			visit = new boolean[101];
			
			if(dfs(visit, start)) count++;		// 이분매칭
			if(count == N) return start;		// 파일 조각 수집 완료시
		}
		
		return -1;
	}
	
	private static boolean dfs(boolean[] visit, int current) {
		if(visit[current]) return false;
		visit[current] = true;
		
		for(int next: connected[current]) {
			if(file[next] == -1 || dfs(visit, file[next])) {
				file[next] = current;
				return true;
			}
		}
		
		return false;
	}
}
