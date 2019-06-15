package brute_force;
import java.util.ArrayList;

/**
 * 
 * 	@author minchoba
 *	백준 4690번: 완전 세제곱
 *
 *	@see https://www.acmicpc.net/problem/4690/
 *
 */
public class Boj4690 {
	private static final String CUBE = "Cube = ";
	private static final String TRIPLE = ", Triple = (";
	private static final String COMMA = ",";
	private static final String NEW_LINE = ")\n";
	
	private static class Elements{
		int b;
		int c;
		int d;
		
		public Elements(int b, int c, int d) {
			this.b = b;
			this.c = c;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws Exception{
		boolean[][][] visit = new boolean[101][101][101];
		
		ArrayList<Elements>[] elem = new ArrayList[101];
		for(int i = 0; i < elem.length; i++) {
			elem[i] = new ArrayList<>();
		}
		
		for(int b = 2; b < 101; b++) {
			for(int c = 2; c < 101; c++) {
				for(int d = 2; d < 101; d++) {
					if(visit[b][c][d]) continue;			// 중복 리스트 제거
					visit[b][c][d] = visit[b][d][c] = visit[c][b][d] = visit[c][d][b] = visit[d][c][b] = visit[d][b][c] = true;
					
					int[] pow = {b * b * b, c * c * c, d * d * d};
					int sum = pow[0] + pow[1] + pow[2];
					
					if(sum > 1_000_000) continue;			// 100만을 넘어가면 불일치
					int a = (int) Math.cbrt(sum);
					
					if(a * a * a == sum) elem[a].add(new Elements(b, c, d));		// 가능한 조합을 인접리스트에 저장
				}
			}
		}
		
		System.out.println(getList(elem));
	}
	
	private static String getList(ArrayList<Elements>[] list) {
		StringBuilder sb = new StringBuilder();
		
		for(int a = 0; a < list.length; a++)
			for(Elements e: list[a]) {
				sb.append(CUBE).append(a).append(TRIPLE).append(e.b).append(COMMA).append(e.c).append(COMMA).append(e.d).append(NEW_LINE);
			}
		
		return sb.toString();
	}
}
