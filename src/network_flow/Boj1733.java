package network_flow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 1733번: 등번호
 *
 *	@see https://www.acmicpc.net/problem/1733/
 *
 */
public class Boj1733 {
	private static ArrayList<Integer>[] connected;
	private static final String NEW_LINE = "\n";
	
	private static int[] aMatch, bMatch, visit;
	private static int visitCnt = 1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		connected = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			connected[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken()) - 1;
			int num2 = Integer.parseInt(st.nextToken()) - 1;
			
			connected[i].add(num1);
			connected[i].add(num2);
		}
		
		System.out.println(bipartiteMatch(N));
	}
	
	private static String bipartiteMatch(int n) {
		visit = new int[n];
		aMatch = new int[n];
		bMatch = new int[1_000_000];
		
		Arrays.fill(aMatch, -1);
		Arrays.fill(bMatch, -1);
		
		for(int i = 0; i < n; i++) {
			visitCnt++;						// check duplicate
			recursion(i);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < connected.length; i++) {
			if(aMatch[i] == -1) return "-1";						// can't match
			sb.append(aMatch[i] + 1).append(NEW_LINE);
		}
		
		return sb.toString();
	}
	
	private static boolean recursion(int current) {
		if(visit[current] == visitCnt) return false;
		visit[current] = visitCnt;
		
		for(int next: connected[current]) {
			if(bMatch[next] == -1 || recursion(bMatch[next])) {		// is matched
				bMatch[next] = current;
				aMatch[current] = next;
				
				return true;
			}
		}
		
		return false;
	}
}
