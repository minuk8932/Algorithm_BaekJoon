package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2193 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[] hitND = new long[N+1];
		
		if(N == 1){
			hitND[1] = 1;
		}
		
		if(N == 2){
			hitND[2] = 1;
		}
		
		for(int i = 3; i <= N; i++){
				hitND[1] = 1;
				hitND[2] = 1;
				
				hitND[i] = hitND[i - 1] + hitND[i - 2];
		}
			
		
		System.out.println(hitND[N]);
	}
}
