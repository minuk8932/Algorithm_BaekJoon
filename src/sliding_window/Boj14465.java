package sliding_window;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14465번: 소가 길을 건너간 이유 5
 *
 *	@see https://www.acmicpc.net/problem/14465/
 *
 */
public class Boj14465 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		boolean[] isBroken = new boolean[N];
		for(int i = 0; i < B; i++) {
			isBroken[Integer.parseInt(br.readLine()) - 1] = true;
		}
		
		System.out.println(fix(N, K, B, isBroken));
	}
	
	private static int fix(int n, int k, int b, boolean[] arr) {
		int min = n;
		int fix = 0;
		int size = 0;
		int right = 0, left = 0;
		
		while(right < n) {
			if(size < k) {
				if(arr[right++]) fix++;		// 연속된 신호등이 k보다 적을 때, 다음칸 수리여부를 파악 후 값++
				size++;
			}
			else {
				min = Math.min(fix, min);	// k보다 크거나 같은 경우, 그때의 최소 수리 횟수
				
				if(arr[left++]) fix--;		// 윈도우 사이즈를 줄임
				size--;
			}
		}
		
		return Math.min(fix, min);
	}
}
