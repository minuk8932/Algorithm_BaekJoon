package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6123번: O Those Fads
 *
 *	@see https://www.acmicpc.net/problem/6123/
 *
 */
public class Boj6123 {
	private static int[] resist;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		resist = new int[N];
		for(int i = 0; i < N; i++) {
			resist[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(counting(L, K));
	}
	
	private static int counting(long l, int k) {
		int count = 0;
		Arrays.sort(resist);
		
		for(int i = 0; i < resist.length; i++) {
			if(l < resist[i]) return count;			// 현 i번째 소의 저항도가 높으면 이후 소들은 유행을 따라가지 않음
			
			l += k;
			count++;
		}
		
		return count;
	}
}
