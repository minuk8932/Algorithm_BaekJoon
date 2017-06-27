package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj5059 {
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());

		while (T > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] items = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine(),  " ");
			for(int i = 0; i < N; i++){
				items[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(items);
			int sum = 0;
			
			for(int i = 0; i < N / 3; i++){
				sum += items[N - 1 - (i * 3) - 2];
				
			}
			
			sb.append(sum).append(NEW_LINE);
			
			T--;
		}
		System.out.println(sb.toString());
	}

}
