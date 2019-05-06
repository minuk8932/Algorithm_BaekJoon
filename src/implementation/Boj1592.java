package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1592번: 영식이와 친구들
 *
 *	@see https://www.acmicpc.net/problem/1592/
 *
 */
public class Boj1592 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		System.out.println(getCount(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
	}
	
	private static int getCount(int N, int M, int L) {
		int count = 0;
		int[] arr = new int[N];
		
		int idx = 0;
		
		while(true) {			
			if(arr[idx] % 2 == 0) idx = idx - L < 0 ? idx - L + N: idx - L;		// 공을 빙글빙글 돌림
			else idx = (idx + L) % N;

			arr[idx]++;					// 받은 횟수
			if(arr[idx] == M) break;
			
			count++;					// 던진 횟수
		}
		
		return count;
	}
}
