package math;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

/**
 * 
 * 	@author exponential-e
 *	백준 17827번: 달팽이 리스트
 *
 *	@see https://www.acmicpc.net/problem/17827/
 *
 */
public class Boj17827 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken()) - 1;
		
		int[] list = new int[N];
		int[] part = new int[N - V];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < list.length; i++) {
			list[i] = Integer.parseInt(st.nextToken());
			
			if(i >= V) part[i - V] = list[i];
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(M-- > 0) {
			int K = Integer.parseInt(br.readLine());
			
			if(K < N) sb.append(list[K]).append(NEW_LINE);					// in range
			else sb.append(part[(K - V) % part.length]).append(NEW_LINE);	// out range
		}
		
		System.out.println(sb.toString());
	}
}
