package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 13244번: Tree
 *
 *	@see https://www.acmicpc.net/problem/13244/
 *
 */
public class Boj13244 {
	private static final String T = "tree";
	private static final String G = "graph";
	private static final String NEW_LINE = "\n";
	
	private static boolean[] visit;
	private static int nodes = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			ArrayList<Integer>[] isGraph = new ArrayList[N];
			for(int i = 0; i < N; i++) {
				isGraph[i] = new ArrayList<>();
			}
			
			int loop = M;
			while(loop-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken()) - 1;
				int B = Integer.parseInt(st.nextToken()) - 1;
				
				isGraph[A].add(B);
				isGraph[B].add(A);		// 양방향 노드 설정
			}
			
			visit = new boolean[N];
			nodes = 1;
			dfs(isGraph, 0);
			
			sb.append(N - 1 != M ? G: nodes == N ? T : G).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
	
	private static void dfs(ArrayList<Integer>[] list, int current) {
		visit[current] = true;
		
		for(int next: list[current]) {		// 연결된 노드의 갯수 카운팅
			if(visit[next]) continue;
			
			nodes++;
			dfs(list, next);
		}
	}
}
