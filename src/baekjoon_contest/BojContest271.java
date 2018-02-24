package baekjoon_contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BojContest271 {
	private static final String SPACE = " ";
	private static final int MOD = 1_000_000_007;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int res = 1;
		
		if(M == 0){
			while(N-- > 0){
				res = (res * 2) % MOD;
			}
			System.out.println(res);
		}
		else{
			int[] L = new int[M + 1];
			int[] R = new int[M + 1];
			int[] K = new int[M + 1];
			
			for(int i = 1; i < M + 1; i++){
				st = new StringTokenizer(br.readLine(), SPACE);
				L[i] = Integer.parseInt(st.nextToken());
				R[i] = Integer.parseInt(st.nextToken());
				K[i] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
