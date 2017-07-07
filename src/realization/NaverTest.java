package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NaverTest {
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] chars = br.readLine().toCharArray();
		
		br.close();
		
		char last = ' ';
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		
		for(final char C : chars){
			if(last != ' ' && last == C){
				cnt++;
			}
			else{
				if(last != ' '){
					sb.append(last).append(cnt);
				}
				
				last = C;
				cnt = 1;
			}
		}
		
		if(last != ' '){
			sb.append(last).append(cnt);
		}
		
		System.out.println(sb.toString());
	}

}
