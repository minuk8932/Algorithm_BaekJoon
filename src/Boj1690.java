import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1690 {
	private static int[] A, B;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		B = new int[N];
		for(int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(br.readLine());
		}
		
		
	}
}
