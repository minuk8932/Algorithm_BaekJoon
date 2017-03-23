package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1812 {
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] candySum = new int[N];
		int solv = 0;
		
		for(int i = 0; i < N; i++){
			candySum[i] = Integer.parseInt(br.readLine());
			if(i == 0) {
				solv = candySum[i];
			}
			else{
				solv = candySum[i] - solv;
			}
		}
		
		int A = solv / 2;
		
		for(int i = 0; i < N; i++){
			if(i == 0){
				sb.append(A).append(NEW_LINE);
			}
			else{
				sb.append(candySum[i-1] - A).append(NEW_LINE);
				A = candySum[i-1] - A;
			}
		}
		System.out.println(sb.toString());
	}

}
