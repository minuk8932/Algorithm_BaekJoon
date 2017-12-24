package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2293 {
	private static final String SPACE = " ";
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] cache = new int[k+1];
		int[] coin = new int[n];
		
		for(int i = 0; i < n; i++){
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		cache[0] = 1;
		
		for(int i = 0; i < n; i++){
			for(int j = coin[i]; j <= k; j++){
				cache[j] += cache[j - coin[i]];
			}
		}
		System.out.println(cache[k]);
	}
}
