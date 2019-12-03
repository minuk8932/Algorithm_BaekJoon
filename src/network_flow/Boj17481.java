package network_flow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17481번: 최애 정하기
 *
 *	@see https://www.acmicpc.net/problem/17481/
 *
 */
public class Boj17481 {
	private static ArrayList<Integer>[] connected;
	private static int[] aMatch, bMatch, visit;
	private static int vcount;
	
	private static HashMap<String, Integer> member = new HashMap<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		connected = new ArrayList[N];
		for(int i = 0; i < M; i++) {
			member.put(br.readLine(), i);
		}
		
		for(int i = 0; i < N; i++) {
			connected[i] = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			int loop = Integer.parseInt(st.nextToken());
			
			while(loop-- > 0) {								// set graph
				String name = st.nextToken();
				connected[i].add(member.get(name));
			}
		}
		
		System.out.println(bipartiteMatch(N, M));
	}
	
	private static String bipartiteMatch(int n, int m) {
		int count = 0;
		
		aMatch = new int[n];
		bMatch = new int[m];
		visit = new int[n];
		
		Arrays.fill(aMatch, -1);
		Arrays.fill(bMatch, -1);
		
		for(int i = 0; i < n; i++) {
			vcount++;
			count += recursion(i);
		}
		
		if(count == n) return "YES";				// all friends have favorite
		
		StringBuilder sb = new StringBuilder();
		sb.append("NO\n").append(count);
		
		return sb.toString();
	}
	
	private static int recursion(int current) {
		if(visit[current] == vcount) return 0;
		visit[current] = vcount;
		
		for(int next: connected[current]) {
			if(bMatch[next] == -1 || recursion(bMatch[next]) == 1) {		// match
				bMatch[next] = current;
				aMatch[current] = next;
				
				return 1;
			}
		}
			
		return 0;
	}
}
