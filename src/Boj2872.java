import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2872 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] book = new int[N];
		for(int i = 0; i < N; i++) {
			book[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(arrange(N, book));
	}
	
	private static int arrange(int n, int[] arr) {
		
		
		return 0;
	}
}
