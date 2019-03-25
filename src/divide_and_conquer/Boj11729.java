package divide_and_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj11729 {
	private static StringBuilder sb = new StringBuilder();
	
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		sb.append((1 << N) - 1).append(NEW_LINE);
		dfs(N, 1, 2, 3);
		
		System.out.print(sb);
	}
	
	private static void dfs(int n, int current, int via, int next) {
		if(n == 0) return;
		
		dfs(n - 1, current, next, via);
		sb.append(current).append(SPACE).append(next).append(NEW_LINE);
		dfs(n - 1, via, current, next);
	}
}

