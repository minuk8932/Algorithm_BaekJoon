package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj9550 {
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int child = 0;
			
			StringTokenizer eachValue = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++){
				int candies = Integer.parseInt(eachValue.nextToken());
				
				child += candies / K;
			}
			sb.append(child).append(NEW_LINE);
			
			T--;
		}
		System.out.println(sb.toString());
	}

}
