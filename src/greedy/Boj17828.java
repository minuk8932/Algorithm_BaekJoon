package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17828번: 문자열 화폐
 *
 *	@see https://www.acmicpc.net/problem/17828/
 *
 */
public class Boj17828 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		System.out.println(getAlpha(N, X));
	}
	
	private static String getAlpha(int n, int x) {
		double div =  x / (double) n;
		if(div < 1 || div > 26) return "!";						// can't make alphabet
		
		int[] result = new int[n];
		int size = n;
		int target = 1, total = 0;
		
		for(int i = 0; i < n; i++) {
			int tmp = -1;
			
			for(; target < 27; target++) {						// if use alphabet 'C' next >= 'C'
				int cur = (size - 1) * 26 + target + total;
				
				if(cur > x) break;
				else tmp = target;
			}
			
			if(target == 27) target--;
			
			result[i] = tmp == -1 ? target: tmp;				// if cur <= x ? tmp has prev data
			total += result[i];
			size--;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			sb.append((char) (result[i] - 1 + 'A'));
		}
		
		return sb.toString();
	}
}
