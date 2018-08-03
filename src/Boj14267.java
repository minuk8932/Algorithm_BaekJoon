import java.util.ArrayList;
import java.util.Scanner;

public class Boj14267 {
	private static final String SPACE = " ";

	private static ArrayList<Integer>[] mem = null;
	private static long[] cost = null;
	private static long[] res = null;
	private static boolean[] isVisited = null;

	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		int n = Integer.parseInt(st.nextToken());
//		int m = Integer.parseInt(st.nextToken());

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		mem = new ArrayList[n];
		isVisited = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			mem[i] = new ArrayList<>();
		}
		
//		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
//			int num = Integer.parseInt(st.nextToken()) - 1;
			int num = sc.nextInt() - 1;
			
			if(num < 0) continue;

			mem[num].add(i);
		}

		cost = new long[n];
		res = new long[n];

		for (int i = 0; i < m; i++) {
//			st = new StringTokenizer(br.readLine());
//			cost[Integer.parseInt(st.nextToken()) - 1] += Long.parseLong(st.nextToken());
			cost[sc.nextInt() - 1] += sc.nextLong();
		}
		
//		br.close();
		
		search(0, cost[0]);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(res[i]).append(SPACE);
		}

		System.out.println(sb.toString());
	}
	
	private static void search(int start, long sum) {
		if(isVisited[start]) return;
		
		isVisited[start] = true;
		long tmpCost = sum;
		
		res[start] = tmpCost;
		
		for(int next: mem[start]) {			
			if(!isVisited[next]) search(next, tmpCost + cost[next]);
		}
	}
}
