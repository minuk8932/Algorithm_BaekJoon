package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj8320 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		int range = (int) Math.floor(Math.sqrt(N));
		
		for(int i = 0; i < range; i++){
			sum += N/(i+1) - i;
		}	
		System.out.println(sum);
	}
}
