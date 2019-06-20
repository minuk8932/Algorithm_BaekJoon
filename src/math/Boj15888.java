package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15888번: 정답은 이수근이야!!
 *
 *	@see https://www.acmicpc.net/problem/15888/
 *
 */
public class Boj15888 {
	private static final String POW_TWO = "이수근";
	private static final String INT = "정수근";
	private static final String VIRTUE = "둘다틀렸근";
	
	private static final int INF = -10_000_000;
	
	private static ArrayList<Div>[] div = new ArrayList[2];
	
	private static class Div{
		int a1;
		int a2;
		
		public Div(int a1, int a2) {
			this.a1 = a1;
			this.a2 = a2;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		System.out.println(getResult(A, B, C));
	}
	
	private static String getResult(int A, int B, int C) {
		makeList(A, 0);				// 방정식에 들어갈 수 있는 수의 집합
		makeList(C, 1);
		
		int aSize = div[0].size(), cSize = div[1].size();
		int[] x = new int[2];
		Arrays.fill(x, INF);
		
		for(int a = 0; a < aSize; a++) {
			for(int c = 0; c < cSize; c++) {
				Div As = div[0].get(a), Cs = div[1].get(c);
				if(hasAnswer(As, Cs, B) == 0) continue;		// 해가 될 수 없는 집합
				
				if(hasAnswer(As, Cs, B) == 1) {						// 해를 발견한 경우
					x[0] = Cs.a2 % As.a1 == 0 ? -Cs.a2 / As.a1: INF;
					x[1] = Cs.a1 % As.a2 == 0 ? -Cs.a1 / As.a2: INF;
				}
				else {
					x[0] = Cs.a1 % As.a1 == 0 ? -Cs.a1 / As.a1: INF;
					x[1] = Cs.a2 % As.a2 == 0 ? -Cs.a2 / As.a2: INF;
				}
			}
		}
		
		if(x[0] == INF || x[1] == INF || x[0] == x[1]) return VIRTUE;		// 중근이거나 두 근 중 하나가 정수가 아닌 경우
		if(justTwo(x[0]) || justTwo(x[1])) return INT;						// 두 근이 1 또는 2의 지수승이 아닌 근을 갖는 경우
		return POW_TWO;
	}
	
	private static void makeList(int val, int idx) {
		div[idx] = new ArrayList<>();
		
		for(int i = 1; i < 101; i++) {
			if(val % i == 0) {
				div[idx].add(new Div(val / i, i));			// 음수 양수 모두 포함
				div[idx].add(new Div(-val / i, -i));
			}
		}
	}
	
	private static int hasAnswer(Div as, Div cs, int b) {		// ad + bc == B ?
		return as.a1 * cs.a1 + as.a2 * cs.a2 == b ? 1: as.a2 * cs.a1 + as.a1 * cs.a2 == b ? -1: 0;
	}
	
	private static boolean justTwo(int loop) {
		loop = Math.abs(loop);
		if(loop == 1) return true;
		
		while(loop > 1) {
			if(loop % 2 == 1) return true;		// 2의 지수승이 아닌 경우
			loop /= 2;
		}
		
		return false;
	}
}
