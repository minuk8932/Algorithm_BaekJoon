package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15881번: Pen Pineapple Apple Pen
 *
 *	@see https://www.acmicpc.net/problem/15881/
 *
 */
public class Boj15881 {
	private static final char[] PPAP = {'p', 'P', 'A', 'p'};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[] words = br.readLine().toCharArray();
		
		System.out.println(getPPAP(n, words));
	}
	
	private static int getPPAP(int N, char[] arr) {
		int count = 0;
		
		for(int i = 0; i < N; i++) {
			if(arr[i] == PPAP[0]) {
				int same = 0;
				int index = i;
				
				for(int x = 0; x < PPAP.length; x++) {
					if(index == N) break;
					if(arr[index++] == PPAP[x]) same++;
				}
				
				if(same == 4) {
					count++;
					i += 3;
				}
			}
		}
		
		return count;
	}
}
