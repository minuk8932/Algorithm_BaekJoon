package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11328번: Strfry
 *
 *	@see https://www.acmicpc.net/problem/11328/
 *
 */
public class Boj11328 {
	private static int[][] alpha;
	
	private static final String P = "Possible";
	private static final String I = "Impossible";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			alpha = new int[2][26];
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(char c: st.nextToken().toCharArray()) {
				alpha[0][c - 'a']++;
			}
			
			for(char c: st.nextToken().toCharArray()) {
				alpha[1][c - 'a']++;
			}
			
			sb.append(isSame(alpha[0], alpha[1]) ? P : I).append(NEW_LINE);		// 갯수가 같으면 변경 가능
		}
		
		System.out.println(sb);
	}
	
	private static boolean isSame(int[] arr1, int[] arr2) {
		for(int i = 0; i < arr1.length; i++) {
			if(arr1[i] != arr2[i]) return false;
		}
		
		return true;
	}
}
