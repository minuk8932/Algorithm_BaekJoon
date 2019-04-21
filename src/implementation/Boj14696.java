package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14696번: 딱지 놀이
 *
 *	@see https://www.acmicpc.net/problem/14696/
 *
 */
public class Boj14696 {
	private static final String NEW_LINE = "\n";
	private static final String A = "A";
	private static final String B = "B";
	private static final String DRAW = "D";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[][] tag = new int[2][4];
			
			int count = Integer.parseInt(st.nextToken());
			while(count-- > 0) {
				tag[0][Integer.parseInt(st.nextToken()) - 1]++;
			}
			
			st = new StringTokenizer(br.readLine());
			count = Integer.parseInt(st.nextToken());
			while(count-- > 0) {
				tag[1][Integer.parseInt(st.nextToken()) - 1]++;
			}
			
			sb.append(compare(tag)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static String compare(int[][] arr) {		
		for(int i = 3; i >= 0; i--) {
			if(arr[0][i] == arr[1][i]) continue;		// 같으면 패스
			
			if(arr[0][i] > arr[1][i]) return A;
			else return B;
		}		
		
		return DRAW;
	}
}
