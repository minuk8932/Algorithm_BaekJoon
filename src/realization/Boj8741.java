package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj8741 {
	private static final int MAX = 1_000_001;
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());

		int[] arr = new int[MAX];
		for(int i = 1; i < k + 1; i++){
			arr[i] = 1;
		}
		
		int loop = k * 2;
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < loop; i++){
			if(i <= loop/2){
				sb.append(1);
			}
			else{
				sb.append(0);
			}
		}
		System.out.println(sb.toString());
	}
}
