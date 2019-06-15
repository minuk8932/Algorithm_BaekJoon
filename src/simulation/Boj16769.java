package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16769번: Mixing Milk
 *
 *	@see https://www.acmicpc.net/problem/16769/
 *
 */
public class Boj16769 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] capacity = new int[3];
		int[] bucket = new int[3];
		
		for(int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			capacity[i] = Integer.parseInt(st.nextToken());
			bucket[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getLast(capacity, bucket));
	}
	
	private static String getLast(int[] c, int[] b) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 100; i++) {					// 옆 양동이로 우유 넘기기
			int current = i % 3, next = (i + 1) % 3;
			int total = b[current] + b[next];
			
			b[next] = total >= c[next] ? c[next]: total;
			b[current] = total >= c[next] ? total - b[next]: 0;
		}
		
		return sb.append(b[0]).append(NEW_LINE).append(b[1]).append(NEW_LINE).append(b[2]).toString();
	}
}
