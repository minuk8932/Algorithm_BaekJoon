import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3908 {
	private static final int TOTAL = 1121;
	private static final int SIZE = 15;
	private static boolean[] notPrime = new boolean[TOTAL];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		isPrime();

		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			int[][] dp = new int[TOTAL][SIZE];
			for(int i = 2; i < TOTAL; i++) {
				if(!notPrime[i]) dp[i][1] = 1;
			}
			
			for(int i = 2; i < TOTAL; i++) {
				int sum = 0;
				
				for(int j = 2; j < TOTAL; j++) {
					if(notPrime[j]) continue;
					
					sum += j;
				}
			}
		}
	}
	
	private static void isPrime() {
		notPrime[0] = notPrime[1] = true;
		
		for(int i = 2; i < TOTAL; i++) {
			if(notPrime[i]) continue;
			
			for(int j = i + i; j < TOTAL; j += i) {
				notPrime[j] = true;
			}
		}
	}
}
