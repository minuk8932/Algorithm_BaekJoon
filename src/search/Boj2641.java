package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2641번: 다각형 그리기
 *
 *	@see https://www.acmicpc.net/problem/2641/
 *
 */
public class Boj2641 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] seq = new int[2][N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			seq[0][i] = Integer.parseInt(st.nextToken());		// 정방향 그리기
			
			switch(seq[0][i]) {				// 역방향 그리기
			case 1:
				seq[1][N - 1 - i] = 3;
				break;
				
			case 2:
				seq[1][N - 1 - i] = 4;
				break;
				
			case 3:
				seq[1][N - 1 - i] = 1;
				break;
				
			case 4:
				seq[1][N - 1 - i] = 2;
				break;
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		int[][] cand = new int[T][N];
		
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				cand[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.print(getSequence(N, T, seq, cand));
	}
	
	private static StringBuilder getSequence(int n, int t, int[][] src, int[][] target) {
		StringBuilder sb = new StringBuilder();
		boolean[] flag = new boolean[t];
		
		int count = 0;
		for(int i = 0; i < t; i++) {			
			flag[i] |= isSame(src[0], target[i]);		// 같은 도형인지
			if(flag[i]) continue;
			
			flag[i] |= isSame(src[1], target[i]);
		}
		
		for(int i = 0; i < t; i++) {
			if(flag[i]) {
				count++;
				
				for(int j = 0; j < n; j++) {
					sb.append(target[i][j]).append(SPACE);
				}
				sb.append(NEW_LINE);
			}
		}
		
		System.out.println(count);
		return sb;
	}
	
	private static boolean isSame(int[] s, int[] t) {
		for(int start = 0; start < s.length; start++) {
			boolean flag = true;
			
			for(int add = 0; add < s.length; add++) {
				if(s[add] != t[(start + add) % s.length]) flag = false;
			}
			
			if(flag) return true;
		}
		
		return false;
	}
}
