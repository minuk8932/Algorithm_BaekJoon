package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 2238번: 경매
 *
 *	@see https://www.acmicpc.net/problem/2238/
 *
 */
public class Boj2238 {
	private static class Auction{
		String name;
		int cost;
		
		public Auction(String name, int cost) {
			this.name = name;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int U = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] value = new int[U + 1];
		Auction[] a = new Auction[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int cost = Integer.parseInt(st.nextToken());
			
			value[cost]++;
			a[i] = new Auction(name, cost);			
		}
		
		System.out.println(winningBid(U, N, value, a));
	}
	
	private static String winningBid(int u, int n, int[] v, Auction[] a) {
		int min = n + 1;
		int val = 0;
		
		for(int i = 0; i < u; i++) {
			if(v[i] == 0) continue;
			if(v[i] < min) {
				min = v[i];
				val = i;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			if(a[i].cost == val) {
				sb.append(a[i].name).append(" ").append(a[i].cost);
				break;
			}
		}
		
		return sb.toString();
	}
}
