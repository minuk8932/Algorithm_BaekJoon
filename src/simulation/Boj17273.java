package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17273번: 카드 공장 (small)
 *
 *	@see https://www.acmicpc.net/problem/17273/
 *
 */
public class Boj17273 {
	
	private static class Card{
		int front;
		int back;
		boolean type;
		
		public Card(int front, int back, boolean type) {
			this.front = front;
			this.back = back;
			this.type = type;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Card[] c = new Card[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			c[i] = new Card(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), true);
		}
		
		while(M-- > 0) {
			int K = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < N; i++) {
				if(c[i].type) {
					if(c[i].front <= K) c[i].type = false;
				}
				else {
					if(c[i].back <= K) c[i].type = true;
				}
			}
		}
		
		int sum = 0;
		for(int i = 0; i < N; i++) {
			if(c[i].type) sum += c[i].front;
			else sum += c[i].back;
		}
		
		System.out.println(sum);
	}
}
