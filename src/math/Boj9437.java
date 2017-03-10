package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj9437 {
	public static final String SPACE = " ";
	public static final String NEW_LINE = "\n";
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
LOOP:	while(true){
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int N = Integer.parseInt(st.nextToken());
			if(N==0){
				break LOOP;
			}
			
			int P = Integer.parseInt(st.nextToken());
			
			int[] page = new int[3];
			
			if(P % 2 == 0){
				page[0] = P - 1;
				page[1] = N + 1 - P;
				page[2] = N + 1 - (P - 1);
				Arrays.sort(page);
				
				sb.append(page[0]).append(SPACE).append(page[1]).append(SPACE).append(page[2]).append(NEW_LINE);
			}
			else {
				page[0] = P + 1;
				page[1] = N + 1 - P;
				page[2] = N + 1 - (P + 1);
				Arrays.sort(page);
				
				sb.append(page[0]).append(SPACE).append(page[1]).append(SPACE).append(page[2]).append(NEW_LINE);
			}
			
		}
		System.out.println(sb.toString());
		
	}
}