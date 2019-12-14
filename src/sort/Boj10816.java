package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 10816번: 숫자 카드2
 *
 *	@see https://www.acmicpc.net/
 *
 */
public class Boj10816 {
	private static final int INF = 20_000_001;
	private static final String SPACE = " ";
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] cards = new int[INF];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(num < 0) cards[INF / 2 - num]++;							// negative num 1_001 ~ 2_000
			else cards[num]++;
		}
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int idx = Integer.parseInt(st.nextToken());
			sb.append(idx < 0 ? cards[INF / 2 - idx]: cards[idx]).append(SPACE);
		}
		
		System.out.println(sb.toString());
	}
}
