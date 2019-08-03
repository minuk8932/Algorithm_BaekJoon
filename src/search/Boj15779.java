package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 15779번: Zigzag
 *
 *	@see https://www.acmicpc.net/problem/15779/
 *
 */
public class Boj15779 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] seq = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getZigZag(N, seq));
	}
	
	private static int getZigZag(int n, int[] arr) {
		int max = 0;
		
		for(int i = 0; i < n - 2; i++) {			
			boolean flag = arr[i] <= arr[i + 1] ? true: false;
			int count = 0;
			
			for(int j = i + 2; j < n; j++) {
				if((flag && arr[j] >= arr[j - 1]) || (!flag && arr[j] <= arr[j - 1])) break;		// 단조 증가 또는 단조 감소인 경우
				
				flag = !flag;
				count++;
			}
			
			if(count > max) max = count;
		}
		
		return max + 2;
	}
}
