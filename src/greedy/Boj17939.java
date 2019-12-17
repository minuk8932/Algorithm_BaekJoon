package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17939번: Gazzzua
 *
 *	@see https://www.acmicpc.net/problem/17939/
 *
 */
public class Boj17939 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] coins = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(profit(N, coins));
	}
	
	private static int profit(int n, int[] c) {
        int[] save = new int[n];
        
		int result = 0;
		int time = 0;
		int sum = 0;
		
		save[n - 1] = c[n - 1];
		
		for(int i = n - 2; i >= 0; i--) {				// next saved
			save[i] = Math.max(save[i + 1], c[i]);
		}		
		
		for(int i = 0; i < n; i++){
			if(c[i] == save[i]){						// sell when max reached
				result += time * c[i] - sum;
				sum = time = 0;
				continue;
			}
			
			sum += c[i];
			time++;
		}
		
		return result < 0 ? 0: result;
	}
}
