package baekjoon_contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BSIS_ProblemF {
	private static final String NEW_LINE = "\n";
	private static final int INF = 1_000_001;
	
	private static StringTokenizer st = null;
	private static StringBuilder sb = new StringBuilder();
	private static long[] damageSet = new long[INF];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q1 = Integer.parseInt(st.nextToken());
		int Q2 = Integer.parseInt(st.nextToken());
		
		long[] tmp = new long[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) {
			long d = Long.parseLong(st.nextToken());
			damageSet[i] = d;
			tmp[i] = damageSet[i];
			damageSet[i] += damageSet[i - 1];
		}
		
		int loop = Q1 + Q2;
		
		while(loop-- > 0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int add = 0;
			
			long res = 0;
			
			if(q == 1) Query1(N, from, to, res);			
			else Query2(N, from, to, add);
		}
		
		System.out.println(sb.toString());
	}
	
	private static void Query1(int N, int from, int to, long result) {
		if(to == N) result = damageSet[N] - damageSet[from - 1];				
		else result = damageSet[to] - damageSet[from - 1];
		
		sb.append(result).append(NEW_LINE);
	}
	
	private static void Query2(int N, int from, int to, int add) {
		add = Integer.parseInt(st.nextToken());
		int rep = 0;
		
		for(int i = from; i < N + 1; i++) {
			if(i < to + 1) rep++;
			
			damageSet[i] += (add * rep);
		}
	}
}
