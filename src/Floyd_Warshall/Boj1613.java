package Floyd_Warshall;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1613 {
	private static final String SPACE = " ";
	private static final String NEW_LINE ="\n";
	
	private static final int PRE = -1;
	private static final int POST = 1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] his = new int[n+1][n+1];
		
		while(k-- > 0){
			st = new StringTokenizer(br.readLine(), SPACE);
			
			int as = Integer.parseInt(st.nextToken());
			int ds = Integer.parseInt(st.nextToken());
			
			his[as][ds] = PRE;
			his[ds][as] = POST;
		}
		
		for(int v = 1 ; v < n+1; v++){
			for(int s = 1; s < n+1; s++){
				for(int e = 1; e < n+1; e++){
					if((his[s][v] == -1 || his[v][e] == -1) && his[s][v] == his[v][e]){
						his[s][e] = -1;
					}
					else if((his[s][v] == 1 || his[v][e] == 1)&& his[s][v] == his[v][e]){
						his[s][e] = 1;
					}
					
				}
			}
		}
		
		int cnt = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(cnt-- > 0){
			st = new StringTokenizer(br.readLine(), SPACE);
			
			int ch1 = Integer.parseInt(st.nextToken());
			int ch2 = Integer.parseInt(st.nextToken());
			
			sb.append(his[ch1][ch2]).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}

}
