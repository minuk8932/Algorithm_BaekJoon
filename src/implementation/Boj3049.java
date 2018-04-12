package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj3049 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		N = N * (N - 1) * (N - 2) * (N - 3) / 24;
		
		System.out.println(N);

	}

}
