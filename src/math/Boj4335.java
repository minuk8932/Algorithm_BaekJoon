package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * 	@author exponential-e
 *	백준 4335번: 숫자 맞추기
 *
 *	@see https://www.acmicpc.net/problem/4335/
 *
 */
public class Boj4335 {
	private static final String H = "too high";
	private static final String TERMINATE = "right on";
	private static final String D_HON = "Stan is dishonest\n";
	private static final String HON = "Stan may be honest\n";
	
	private static class Pair{
		int val;
		boolean ans;
		
		public Pair(int val, boolean ans) {
			this.val = val;
			this.ans = ans;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) break;
			
			ArrayList<Pair> offLine = new ArrayList<>();
			int correct = 0;
			
			while(true) {
				String cmd = br.readLine();
				if(cmd.contentEquals(TERMINATE)) {			// 미리 받아 두면서 정답 저장
					correct = num;
					break;
				}
				
				offLine.add(new Pair(num, cmd.equals(H) ? true: false));			// true: ac > val, false: ac < val
				num = Integer.parseInt(br.readLine());
			}
			
			sb.append(answer(offLine, correct));
		}
		
		System.out.print(sb.toString());
	}
	
	private static String answer(ArrayList<Pair> pList, int ac) {
		for(Pair p: pList) {
			if(p.ans) {
				if(p.val <= ac) return D_HON;			// 생각한 답과 다르게 얘기한 경우
			}
			else {
				if(p.val >= ac) return D_HON;
			}
		}
		
		return HON;				// 정직한 경우
	}
}
