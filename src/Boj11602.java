import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11602 {
	private static long[] cards = null;
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			cards = new long[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				cards[i] = Long.parseLong(st.nextToken());
			}
			
			Arrays.sort(cards);
			
			long res = 0;
			
			if(N == 1) {
				res = cards[0];
			}
			else {
				for(int i = 0; i < N / 2; i += 2) {
					res += (cards[i] + cards[N - 1 - i]);
				}
				
				if(N % 2 == 1) {
					if(N % 4 == 1) {
						res += cards[N / 2 + 1];
					}
				}
			}
			
			sb.append(res).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}
