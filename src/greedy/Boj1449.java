package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1449번: 수리공 항승
 *
 *	@see https://www.acmicpc.net/problem/1449/
 *
 */
public class Boj1449 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken()) - 1;			// remove each side 0.5
		
		int tapes = 0;
		int[] leak = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			leak[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(leak);

		for(int i = 0; i < N; i++) {
			int tmp=0;
			tapes++;
			
			for(int j = i + 1; j < N; j++) {
				if(leak[i] + L >= leak[j]) tmp++;				// leak[i] + L >= leak[j], can covered
				else break;
			}
			i += tmp;
		}
		
		System.out.println(tapes);
	}
}
