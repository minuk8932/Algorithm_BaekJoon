package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 9492번: Perfect Shuffle
 *
 *	@see https://www.acmicpc.net/problem/9492/
 *
 */
public class Boj9492 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			String[] cards = new String[N];
			for(int i = 0; i < N; i++) {
				cards[i] = br.readLine();
			}
			
			int flag = N % 2 == 1 ? 1: 0;
			// shuffle
			for(int i = 0; i < N / 2; i++) {
				sb.append(cards[i]).append(NEW_LINE).append(cards[(cards.length / 2) + flag + i]).append(NEW_LINE);
			}
			
			if(flag == 1) sb.append(cards[cards.length / 2]).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}
