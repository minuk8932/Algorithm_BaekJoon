package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj9461 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		long[] waves;
		
		StringBuilder sb = new StringBuilder();
		while(T-- > 0){
			int N = Integer.parseInt(br.readLine());
			waves = new long[N+1];
			
			if(N == 1){
				waves[1] = 1;
			}			
			if(N == 2){
				waves[2] = 1;
			}
			if(N == 3){
				waves[3] = 1;
			}
			
			if(N >= 4){
				waves[1] = 1;
				waves[2] = 1;
				waves[3] = 1;
				for(int i = 4; i < N+1; i++){
					waves[i] = waves[i - 2] + waves[i - 3];
				}
			}
			
			sb.append(waves[N]).append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}

}
