package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj4948 {
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
LOOP:	while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0){
				break LOOP;
			}
			
			boolean[] arr = new boolean[(2 * N) + 1];
			for(int i = N + 1; i <= 2 * N; i++){
				arr[i] = true;
			}
			
			for(int i = 2; i <= 2 * N; i++){
		        for(int j = i*2; j <= 2 * N; j+=i)
		            arr[j] = false;
			}
			
			int cnt = 0;
			for(int i = N+1; i <= 2 * N; i++){
				if(arr[i]){
					cnt++;
				}
			}
			sb.append(cnt).append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}
}
