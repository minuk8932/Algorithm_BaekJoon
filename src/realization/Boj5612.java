package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5612 {
	public static final String NEW_LINE = "\n";
	
	public static void main (String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int max = 0, chk = 0;
		
		for(int i =0; i < n; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			m += Integer.parseInt(st.nextToken());
			m -= Integer.parseInt(st.nextToken());
			
			max = Math.max(m, max);
			if(m < 0){
				chk++;
			}
		}
		
		if(chk > 0){
			max = 0;
			sb.append(max).append(NEW_LINE);
		}
		else {
			sb.append(max).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}

}
