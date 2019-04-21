package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10834번: 벨트
 *
 *	@see https://www.acmicpc.net/problem/10834/
 *
 */
public class Boj10834 {
	private static ArrayList<State> list = new ArrayList<>();
	
	private static class State{
		int left;
		int right;
		int type;
		
		public State(int left, int right, int type) {
			this.left = left;
			this.right = right;
			this.type = type;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		
		while(M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			list.add(new State(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		getResult();
	}
	
	private static void getResult() {
		State start = list.remove(0);
		
		long spin = start.right;		// 처음 벨트 상태로
		int type = start.type;
		
		for(State s: list) {
			if(spin == s.left) {				// 다음 벨트의 회전률을 연속적으로 구함
				spin = s.right;
			}
			else {
				long gcd = getGcd(spin, s.left);
				spin = s.right * spin / gcd;
			}
			
			type ^= s.type;						// 벨트 상태
		}
		
		System.out.println(type + " " + spin);
	}
	
	private static long getGcd(long a, long b) {
		if(b == 0) return a;
		return getGcd(b, a % b);
	}
}
