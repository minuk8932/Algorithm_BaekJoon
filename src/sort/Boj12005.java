package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12005번: Diamond Collector(Bronze)
 *
 *	@see https://www.acmicpc.net/problem/12005/
 *
 */
public class Boj12005 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] diamond = new int[N];
		for(int i = 0; i < N; i++) {
			diamond[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(count(N, K, diamond));
	}
	
	private static int count(int n, int k, int[] arr) {
		Arrays.sort(arr);				// 최솟값 우선적으로 체크
		int max = 0;
		
		for(int i = 0; i < n; i++) {
			int count = 1;
			
			for(int j = i + 1; j < n; j++) {
				int diff = Math.abs(arr[i] - arr[j]);
				if(diff > k) break;						// 차이가 k를 넘으면 다음부턴 체크할 필요 없음
				count++;
			}
			
			if(count > max) max = count;
		}
		
		return max;
	}
}
