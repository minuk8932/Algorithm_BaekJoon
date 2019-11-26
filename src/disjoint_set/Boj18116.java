package disjoint_set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 18116번: 로봇 조립
 *
 *	@see https://www.acmicpc.net/problem/18116/
 *
 */
public class Boj18116 {
	private static int[] parent = new int[1_000_000];
	
	private static final String QUERY = "Q";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		init();
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			if(st.nextToken().equals(QUERY)) {
				sb.append(-parent[find(Integer.parseInt(st.nextToken()) - 1)]).append(NEW_LINE);		// get size
			}
			else {
				merge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);		// same robot's part
			}
		}
		
		System.out.println(sb.toString());
	}
	
	private static void init() {
		for(int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}
}
