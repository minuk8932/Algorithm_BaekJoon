package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16396번: 선 그리기
 *
 *	@see https://www.acmicpc.net/problem/16396/
 *
 */
public class Boj16396 {
	private static final int INF = 10_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		boolean[] section = new boolean[INF];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			for(int j = from; j < to; j++) {			// 선 그리기
				section[j] = true;
			}
		}
		
		System.out.println(getLength(section));		// 선 길이 출력
	}
	
	private static int getLength(boolean[] s) {
		int leng = 0;
		
		for(int i = 0; i < INF; i++) {
			if(s[i]) leng++;
		}
		
		return leng;
	}
}
