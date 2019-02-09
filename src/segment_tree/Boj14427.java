package segment_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14427번: 수열과 쿼리 15
 *
 *	@see https://www.acmicpc.net/problem/14427/
 *
 */
public class Boj14427 {
	private static final String NEW_LINE = "\n";
	private static final int INF = 1_000_000_001;
	private static Pair[] seg;
	
	private static class Pair{
		int value;
		int idx;
		
		public Pair(int value, int idx) {
			this.value = value;
			this.idx = idx;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		int S = 1;
		while(S < N) S <<= 1;
		
		seg = new Pair[S * 2];
		for(int i = 0; i < seg.length; i++) {
			seg[i] = new Pair(INF, i);
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = S; i < S + N; i++) {
			seg[i] = new Pair(Integer.parseInt(st.nextToken()), i);
		}
		
		init();
		
		int M = Integer.parseInt(br.readLine());
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());
			int index = 0, chVal = 0;
			
			if(query == 1) {
				index = Integer.parseInt(st.nextToken());
				chVal = Integer.parseInt(st.nextToken());
				
				change(index + S - 1, chVal);			// 1의 경우 값 변경
			}
			else {
				sb.append(seg[1].idx - S + 1).append(NEW_LINE);		// 2의 경우 최소 값의 인덱스
			}
		}
		
		System.out.println(sb);
	}
	
	private static void init() {
		for(int i = seg.length - 1; i > 0; i -= 2) {
			Pair p = getPair(i, -1);			
			seg[i / 2] = new Pair(p.value, p.idx);
		}
	}
	
	private static void change(int target, int num) {
		seg[target] = new Pair(num, target);
		
		while(target / 2 != 0) {
			Pair p = new Pair(-1, -1);
			
			if(target % 2 == 0) p = getPair(target, 1);		// 짝수와 홀수 인덱스 구분해서 값 지정
			else p = getPair(target, -1);
			
			seg[target / 2] = new Pair(p.value, p.idx);
			target /= 2;
		}
	}
	
	private static Pair getPair(int target, int adder) {
		int value = Math.min(seg[target].value, seg[target + adder].value);
		int idx = seg[target].value > seg[target + adder].value ? seg[target + adder].idx : seg[target].idx;
		if(seg[target].value == seg[target + adder].value) idx = seg[target].idx < seg[target + adder].idx ? seg[target].idx : seg[target + adder].idx;
		
		return new Pair(value, idx);
	}
}
