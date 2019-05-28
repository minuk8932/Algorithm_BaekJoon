package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12782번: 비트 우정 지수
 *
 *	@see https://www.acmicpc.net/problem/12782/
 *
 */
public class Boj12782 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char[] N = st.nextToken().toCharArray();
			char[] M = st.nextToken().toCharArray();
			
			sb.append(getValue(N, M)).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static int getValue(char[] n, char[] m) {
		int[] diff = new int[2];
		
		for(int i = 0; i < n.length; i++) {
			if(n[i] == m[i]) continue;
			diff[n[i] - '0']++;					// 1과 0에 대한 차이의 갯수를 저장
		}
		
		return Math.max(diff[0], diff[1]);		// 변경 최소 횟수
	}
}
