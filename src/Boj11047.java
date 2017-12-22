import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11047 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] val = new int[N + 1];
		int cnt = 0;
		
		for(int i = 1; i < N + 1; i++){
			val[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = N; i > 0; i--){
			if(K >= val[i]){
				cnt += K / val[i];
				K = K % val[i];
			}
		}
		
		System.out.println(cnt);
	}
}
