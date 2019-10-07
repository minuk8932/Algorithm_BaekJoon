package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 1062번: 가르침
 *
 *	@see https://www.acmicpc.net/problem/1062/
 *
 */
public class Boj1062 {
	private static int[] alpha;
	private static final char[] DEF = {'a', 'c', 'i', 'n', 't'};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		alpha = new int[N];
		for(int i = 0; i < N; i++) {
			char[] input = br.readLine().toCharArray();
			
			for(int c = 0; c < input.length; c++) {					// ith learned words
				alpha[i] |= (1 << (input[c] - 'a'));
			}
		}
		
		System.out.println(recursion(0, K, 0));
	}
	
	private static int recursion(int idx, int k, int learned) {
		if(k < 0) return 0;
		if(idx == 26) {
			int count = 0;
			
			for(int val: alpha) {
				if((val & ((1 << 26) - 1 - learned)) != 0) continue;
				count++;
			}
			
			return count;
		}
		
		int res = 0;
		int tmp = recursion(idx + 1, k - 1, learned | (1 << idx));		// have to learn
		if(res < tmp) res = tmp;
		
		boolean flag = true;
		for(char c: DEF) {												// check needs
			if(idx == c - 'a') flag = true;
		}
		
		if(flag) {														// pass
			tmp = recursion(idx + 1, k, learned);
			if(res < tmp) res = tmp;
		}
		
		return res;
	}
}
