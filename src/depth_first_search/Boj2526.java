package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2526번: 싸이클
 *
 *	@see https://www.acmicpc.net/problem/2526/
 *
 */
public class Boj2526 {
	private static int[] visit = new int[1_000_001];
	private static int total = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		System.out.println(calculate(N, N, P));
	}
	
	private static int calculate(int target, int current, int mod) {
		int next = (target * current) % mod;
		
		if(visit[next] != 0) return total - visit[next] + 1;		// 사이클 생성 시점부터 총 갯수
		visit[next] = visit[current] + 1;
		total++;
		
		return calculate(target, next, mod);			// 반복
	}
}
