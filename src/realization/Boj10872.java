package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj10872 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int factorial = 1;
		
		while(N > 0){
			factorial *= N;
			N--;
		}
		
		System.out.println(factorial);
	}

}
