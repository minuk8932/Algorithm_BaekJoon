package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 13413번: 오셀로 재배치
 *
 *	@see https://www.acmicpc.net/problem/13413/
 *
 */
public class Boj13413 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			boolean[] source = new boolean[N];
			boolean[] target = new boolean[N];
			
			String line = br.readLine();
			for(int i = 0; i < N; i++) {
				if(line.charAt(i) == 'W') source[i] = true;
			}
			
			line = br.readLine();
			for(int i = 0; i < N; i++) {
				if(line.charAt(i) == 'W') target[i] = true;
			}
			
			sb.append(compare(N, source, target)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int compare(int n, boolean[] s, boolean[] t) {
		int B = 0;
		int W = 0;
		
		for(int i = 0; i < n; i++) {
			if(s[i] ^ t[i]) {			// 서로 색이 다른 경우
				if(t[i]) W++;			// 타겟의 흰색과 검정색 갯수를 세어줌
				else B++;
			}
		}
		
		int min = Math.min(B, W);
		int max = Math.max(B, W);
		
		return min + (max - min);		// 결과 공식
	}
}
