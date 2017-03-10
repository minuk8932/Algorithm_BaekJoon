package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10539 {
	public static final String SPACE = " "; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N]; 
		int chk = 0;
		int[] sum = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for(int i = 0; i < N; i++){
			A[i] = Integer.parseInt(st.nextToken());
			
			if(i >= 1){
				sum[i] = ((i+1) * A[i]) - chk;
			} else {
				sum[i] = A[0];
			}
			chk += sum[i];
			
			sb.append(sum[i]).append(SPACE);
		}

		System.out.println(sb.toString());
	}

}
