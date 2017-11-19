import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11722 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] max = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(st.nextToken());
			max[0] = Math.max(max[0], arr[i]);
		}
		
		
		
	}
}
