package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12756번: 고급 레스토랑
 *
 *	@see https://www.acmicpc.net/problem/12756/
 *
 */
public class Boj12756 {
	private static final String A_WIN = "PLAYER A";
	private static final String B_WIN = "PLAYER B";
	private static final String D = "DRAW";
	
	private static class Card{
		int atk;
		int life;
		
		public Card(int atk, int life) {
			this.atk = atk;
			this.life = life;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Card A = new Card(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		st = new StringTokenizer(br.readLine());
		Card B = new Card(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		int resultA = A.life % B.atk == 0 ? A.life / B.atk: A.life / B.atk + 1;			// 누가 생존 ?
		int resultB = B.life % A.atk == 0 ? B.life / A.atk: B.life / A.atk + 1;
		
		System.out.println(resultA == resultB ? D : resultA > resultB ? A_WIN: B_WIN);
	}
}
