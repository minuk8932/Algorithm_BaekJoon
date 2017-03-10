package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1789 {
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		long S = Long.parseLong(br.readLine());
		long N = 0;
		
LOOP:	while(true){
			N++;
			if(S >= N*(N+1)/2 && S <= N*(N+3)/2){
				sb.append(N).append(NEW_LINE);
				
				break LOOP;
			}
		}
		System.out.println(sb.toString());
	}

}
