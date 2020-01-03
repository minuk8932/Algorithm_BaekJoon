package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 6030번: Scavanger Hunt
 *
 *	@see https://www.acmicpc.net/problem/6030/
 *
 */
public class Boj6030 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int P = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		System.out.println(datas(P, Q));
	}
	
	private static String datas(int n, int m) {
		StringBuilder sb = new StringBuilder();
		
		ArrayList<Integer> as = new ArrayList<>();
		ArrayList<Integer> bs = new ArrayList<>();
		
		for(int i = 1; i <= n; i++) {		// get factors
			if(n % i == 0) as.add(i);
		}
		
		for(int j = 1; j <= m; j++) {
			if(m % j == 0) bs.add(j);
		}
		
		for(int a: as) {
			for(int b: bs) {
				sb.append(a).append(SPACE).append(b).append(NEW_LINE);
			}
		}
		
		return sb.toString();
	}
}
