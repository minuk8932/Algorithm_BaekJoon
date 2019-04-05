package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13226번: Divisor Again
 *
 *	@see https://www.acmicpc.net/problem/13226/
 *
 */
public class Boj13226 {
	private static final String NEW_LINE = "\n";
	private static final int INF = 10_000_000;
	private static int[] count = new int[INF + 1];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		init();				// 미리 약수 갯수 구하기 nlogn
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(process(a, b)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static void init() {
		for(int i = 1; i < INF; i++) {
			for(int j = 1; j < (INF / i) + 1; j++) {
				count[i * j]++;
			}
		}
	}
	
	private static int process(int left, int right) {
		int max = 0;
		
		for(int i = left; i < right + 1; i++) {
			if(max < count[i]) max = count[i];
		}
		
		return max;
	}
}
