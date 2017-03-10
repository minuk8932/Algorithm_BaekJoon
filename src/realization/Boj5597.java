package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj5597 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] numbs = new boolean[31];
		
		for(int i = 0; i < 28; i++) {
			numbs[Integer.parseInt(br.readLine())] = true;
		}
		
		br.close();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < 30; i++) {
			if(!numbs[i]) {
				sb.append(i).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());
	}
}