package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1748 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int log10N = (int) Math.floor(Math.log10(N)); 
		
		int res = 0;
		
		for(int i = 0; i < log10N; i++){
			res += 9 * (int) Math.pow(10, i) * (i + 1);
		}
		
		res += (N - (int) Math.pow(10, log10N) + 1) * (log10N + 1);
		System.out.println(res);
		
	}

}
