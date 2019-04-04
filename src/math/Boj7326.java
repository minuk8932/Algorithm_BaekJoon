package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 7326번: Number Steps
 *
 *	@see https://www.acmicpc.net/problem/7326/
 *
 */
public class Boj7326 {
	private static int[][] coordinate = new int[5_001][5_001];
	private static final String NEW_LINE = "\n";
	private static final String NO_ANSWER = "No Number";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		init();
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int value = coordinate[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
			sb.append(value == -1 ? NO_ANSWER: value).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static void init() {
		for(int i = 0; i < coordinate.length; i++) {
			Arrays.fill(coordinate[i], -1);
		}
		
		coordinate[0][0] = 0;
		int adder = 0, count = 0;
		int size = coordinate.length * 2 - 2;
		
		for(int i = 1; i < size; i++) {						// (5000, 5000) 까지 값을 규칙에 맞게 저장
			count++;
			
			if(i % 2 == 1) coordinate[i / 2 + 1][i / 2 + 1] = switching(count, 4, i, 1);
			else coordinate[i - adder][adder++] = switching(count, 4, i, -1);
		}
	}
	
	private static int switching(int loop, int mod, int index, int adder) {
		return loop % mod == 3 || loop % mod == 0 ? index + adder: index;
	}
}
