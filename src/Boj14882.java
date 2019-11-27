import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14882 {
	private static final int MOD = 786433;
	private static int[] prefix = new int[MOD];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] coef = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N + 1; i++) {
			coef[i] = Integer.parseInt(st.nextToken());
		}
		
		int K = Integer.parseInt(br.readLine());
		
		
		st = new StringTokenizer(br.readLine());
		while(K-- > 0) {									// 최종 등차는 a_n * x^n을 끝까지 미분했을때의 계수
			int x = Integer.parseInt(st.nextToken());
		}
	}
}
