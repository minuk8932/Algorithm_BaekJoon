package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10378번: Grave
 *
 *	@see https://www.acmicpc.net/problem/10378/
 *
 */
public class Boj10378 {
	
	private static class Pair{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Pair[] grave = input(br.readLine(), 2);
		Pair[] chapel = input(br.readLine(), 2);
		Pair newGrave = input(br.readLine());
		
		System.out.println(isValid(grave, chapel, newGrave));
	}
	
	private static Pair[] input(String line, int size) {
		StringTokenizer st = new StringTokenizer(line);
		Pair[] in = new Pair[size];
		
		for(int i = 0; i < size; i++) {
			in[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		return in;
	}
	
	private static Pair input(String line) {
		StringTokenizer st = new StringTokenizer(line);	
		return new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	}
	
	private static String isValid(Pair[] g, Pair[] c, Pair nG) {
		if(((nG.y <= c[0].y - g[0].y || nG.y <= g[1].y - c[1].y) && nG.x <= g[1].x - g[0].x)			// 새 무덤 추가 가능한 경우
				|| (nG.y <= g[1].y - g[0].y && (nG.x <= c[0].x - g[0].x || nG.x <= g[1].x - c[1].x))) return "Yes";
		return "No";
	}
}
