package hashing;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17479번: 정식당
 *
 *	@see https://www.acmicpc.net/problem/17479/
 *
 */
public class Boj17479 {
	private static final int SPECIAL = 20_000;
	private static final int SERVICE = 50_000;
	
	private static HashMap<String, Integer> norm = new HashMap<>();
	private static HashMap<String, Integer> spec = new HashMap<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine());
			norm.put(st.nextToken(), Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine());
			spec.put(st.nextToken(), Integer.parseInt(st.nextToken()));
		}
	
		for(int i = 0; i < C; i++) {
			br.readLine();
		}
		
		int N = Integer.parseInt(br.readLine());
		long scost = 0, ncost = 0;
		int count = 0;
		
		while(N-- > 0) {
			String order = br.readLine();
			
			if(spec.containsKey(order)) scost += spec.get(order);
			else if(norm.containsKey(order)) ncost += norm.get(order);
			else count++;
		}
		
		System.out.println(isAlright(scost, ncost, count) ? "Okay": "No");
	}
	
	private static boolean isAlright(long s, long n, int c) {
		if(c > 1) return false;
		if(n < SPECIAL) {					// just normal
			if(s > 0) {
				return false;
			}
			else {
				if(c != 0) return false;	// no service
				else return true;
			}
		}
		else {
			if(s + n < SERVICE) {			// normal + special but, lack
				if(c != 0) return false;
				else return true;
			}
			else {							// normal + special
				return true;
			}
		}
	}
}
