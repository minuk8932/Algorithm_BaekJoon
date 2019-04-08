package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16435번: 스네이크버드
 *
 *	@see https://www.acmicpc.net/problem/16435/
 *
 */
public class Boj16435 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] apples = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			apples[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(growUp(N, L, apples));
	}
	
	private static int growUp(int n, int snake, int[] feed) {
		Arrays.sort(feed);
		
		for(int i = 0; i < n; i++) {
			if(snake < feed[i]) break;		// 닿지 못하면 종료
			snake++;
		}
		
		return snake;
	}
}
