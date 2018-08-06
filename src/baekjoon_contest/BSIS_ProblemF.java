package baekjoon_contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BSIS_ProblemF {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q1 = Integer.parseInt(st.nextToken());
		int Q2 = Integer.parseInt(st.nextToken());
		
		int[] damageSet = new int[N + 1];
		int[] damage = new int[N + 1];
		int sumD = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) {
			int d = Integer.parseInt(st.nextToken());
			damageSet[i] = damage[i] = d;
			sumD += damage[i];
			
			damageSet[i] += damageSet[i - 1];
		}
		
		int loop = Q1 + Q2;
		StringBuilder sb = new StringBuilder();
		
		while(loop-- > 0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int add = 0;
			
			int res = 0;
			
			if(q == 1) {
				if(to == N || from == 1) {
					res = sumD - damageSet[from - 1];
				}
				
				else {
					res = sumD - damageSet[to] + damageSet[from - 1];
				}
				
				sb.append(res).append(NEW_LINE);
			}
			
			else {
				add = Integer.parseInt(st.nextToken());
				
				for(int i = from; i < to + 1; i++) {
					damageSet[i] += add;
				}
				
				sumD += (to - from + 1) * add;
			}
		}
		
		System.out.println(sb.toString());
	}
}
