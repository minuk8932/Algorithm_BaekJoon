import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1790 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int log10N = (int) Math.floor(Math.log10(N)); 
		
		int res = 0;
		
		for(int i = 0; i < log10N; i++){
			res += 9 * (int) Math.pow(10, i) * (i + 1);
		}
		
		res += (N - (int) Math.pow(10, log10N) + 1) * (log10N + 1);
		
		System.out.println(res);
		
		for(int i = 0; i < res; i++){
			
		}
	}
}
