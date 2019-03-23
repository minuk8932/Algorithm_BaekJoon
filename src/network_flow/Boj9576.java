package network_flow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9576번: 책 나눠주기
 *
 *	@see https://www.acmicpc.net/problem/9576/
 *
 */
public class Boj9576 {
	private static ArrayList<Integer>[] connected;
	private static final String NEW_LINE = "\n";
	private static int[] book;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			connected = new ArrayList[M];
			for(int i = 0; i < M; i++) {
				connected[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				
				for(int j = a; j < b + 1; j++) {		// 범위 내 가능한 책 번호 모두 입력
					connected[i].add(j);
				}
			}
			
			sb.append(networkFlow(N, M)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int networkFlow(int n, int m) {
		book = new int[n];
		Arrays.fill(book, -1);
		
		boolean[] visit = new boolean[m];
		int result = 0;
		for(int i = 0; i < m; i++) {
			Arrays.fill(visit, false);
			if(dfs(visit, i)) result++;
		}
		
		return result;
	}
	
	private static boolean dfs(boolean[] visit, int current) {
		if(visit[current]) return false;
		visit[current] = true;
		
		for(int next: connected[current]) {
			if(book[next] == -1 || dfs(visit, book[next])) { 		// 책이 할당되기 전 또는 다른 사람에게 책을 줄 수 있는 경우
				book[next] = current;
				return true;
			}
		}
		
		return false;
	}
}
