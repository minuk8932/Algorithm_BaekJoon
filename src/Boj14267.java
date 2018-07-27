import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj14267 {
	private static final String SPACE = " ";

	private static ArrayList<Integer>[] mem = null;
	private static long[] cost = null;

	private static final int INF = 100_001;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		mem = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) {
			mem[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		int num = 0;
		
		for (int i = 1; i < n + 1; i++) {
			num = Integer.parseInt(st.nextToken());

			if(num == -1) continue;
			mem[num].add(i);
		}

		cost = new long[INF];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			cost[Integer.parseInt(st.nextToken())] += Long.parseLong(st.nextToken());
		}
		
		br.close();
		
		search(1);

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < n + 1; i++) {
			sb.append(cost[i]).append(SPACE);
		}

		System.out.println(sb.toString());
	}
	
	private static void search(int start) {		
		for(int next: mem[start]) {
			if(next == 0) continue;
			
			cost[next] += cost[start];
			search(next);
		}
	}
}
