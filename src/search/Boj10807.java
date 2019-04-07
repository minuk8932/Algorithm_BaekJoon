package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10807번: 개수 세기
 *
 *	@see https://www.acmicpc.net/problem/10807/
 *
 */
public class Boj10807 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] count = new int[201];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			num = num < 0 ? 100 - num: num;
			
			count[num]++;
		}
		
		int v = Integer.parseInt(br.readLine());
		v = v < 0 ? 100 - v: v;
		
		System.out.println(count[v]);
	}
}
