package network_flow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 18138번: 리유나는 세일러복을 좋아해
 *
 *	@see https://www.acmicpc.net/problem/18138/
 *
 */
public class Boj18138 {
	private static int[] aMatch, bMatch;
	private static boolean[] visit;
	private static ArrayList<Integer>[] connected;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		double[] tw = new double[N];
		for(int i = 0; i < N; i++) {
			tw[i] = Double.parseDouble(br.readLine());
		}
		
		double[] sw = new double[M];
		for(int i = 0; i < M; i++) {
			sw[i] = Double.parseDouble(br.readLine());
		}
		
		connected = new ArrayList[M];
		for(int i = 0; i < M; i++) {
			connected[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {					// make graph
			for(int j = 0; j < N; j++) {
				if((tw[j] * 0.5 <= sw[i] && tw[j] * 0.75 >= sw[i]) || (tw[j] <= sw[i] && tw[j] * 1.25 >= sw[i])) {
					connected[i].add(j);
				}
			}
		}
		
		System.out.println(bipartiteMatch(N, M, tw, sw));
	}
	
	private static int bipartiteMatch(int n, int m, double[] t, double[] s) {
		int count = 0;
		
		aMatch = new int[n];
		bMatch = new int[m];
		
		Arrays.fill(bMatch, -1);
		Arrays.fill(aMatch, -1);
		
		for(int i = 0; i < m; i++) {
			visit = new boolean[m];
			count += recursion(i);
		}
		
		return count;
	}
	
	private static int recursion(int current) {
		if(visit[current]) return 0;
		visit[current] = true;
		
		for(int next: connected[current]) {
			if(aMatch[next] == -1 || recursion(aMatch[next]) == 1) {		// matching
				aMatch[next] = current;
				bMatch[current] = next;
				
				return 1;
			}
		}
		
		return 0;
	}
}
