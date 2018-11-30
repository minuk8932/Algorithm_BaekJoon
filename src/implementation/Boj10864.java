package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10864번: 친구
 *
 *	@see https://www.acmicpc.net/problem/10864/
 *
 */
public class Boj10864 {
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] relate = new int[N + 1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			relate[Integer.parseInt(st.nextToken())]++;
			relate[Integer.parseInt(st.nextToken())]++;
		}
		
		System.out.println(getResult(relate));		// 결과 출력
	}
	
	private static StringBuilder getResult(int[] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < arr.length; i++) {
			sb.append(arr[i]).append(NEW_LINE);
		}
		
		return sb;
	}
}
