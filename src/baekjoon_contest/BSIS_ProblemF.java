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
		
		long[] damageSet = new long[N + 1];
		long[] tmp = new long[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) {
			long d = Long.parseLong(st.nextToken());
			damageSet[i] = (long) d;
			tmp[i] = damageSet[i];
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
			
			long res = 0;
			
			if(q == 1) {
				if(to == N) {	
					res = damageSet[N] - damageSet[from - 1];
				}
				
				else {
					res = damageSet[to] - damageSet[from - 1];
				}
				
				sb.append(res).append(NEW_LINE);
			}
			
			else {
				add = Integer.parseInt(st.nextToken());
				int rep = 0;
				
				for(int i = from; i < N + 1; i++) {
					if(i < to + 1) {
						rep++;
					}
					
					damageSet[i] += (add * rep);
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}
