package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14709번: 여우 사인
 *
 *	@see https://www.acmicpc.net/problem/14709
 *
 */
public class Boj14709 {
	private static boolean[][] set = new boolean[5][5];
	
	private static final String AC = "Wa-pa-pa-pa-pa-pa-pow!";
	private static final String WA = "Woof-meow-tweet-squeek";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			set[a][b] = set[b][a] = true;
		}
		
		System.out.println(isFoxSign() ? AC: WA);		// 문제를 제대로 읽자
	}
	
	private static boolean isFoxSign() {
		int count = 0;
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(set[i][j]) count++;
			}
		}
		
		return count == 6 && set[0][2] && set[2][3] && set[3][0] ? true: false;
	}
}
