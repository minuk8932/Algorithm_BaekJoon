package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 6212번: Dream Counting
 *
 *	@see https://www.acmicpc.net/problem/6212/
 *
 */
public class Boj6212 {
	private static final String SPACE = " ";
	private static int[] components = new int[10];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		System.out.println(components(N, M));
	}
	
	private static String components(int n, int loop) {
		StringBuilder sb = new StringBuilder();
		
		while(loop <= n) {
			int value = loop;
			
			while(value > 0) {					// 각 구성 요소 갯수
				components[value % 10]++;
				value /= 10;
			}
			
			loop++;
		}
		
		for(int i = 0; i < 10; i++) {
			sb.append(components[i]).append(SPACE);
		}
		
		return sb.toString();
	}
}
