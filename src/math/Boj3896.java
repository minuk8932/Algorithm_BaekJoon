package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 3896번: 소수 사이 수열
 *
 *	@see https://www.acmicpc.net/problem/3896/
 *
 */
public class Boj3896 {
	private static final int INF = 1_300_000;
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		boolean[] isPrime = getPrime();
		
		while(T-- > 0) {
			int k = Integer.parseInt(br.readLine());
			
			if(isPrime[k]) {
				sb.append(0).append(NEW_LINE);
			}
			else {
				sb.append(getRange(isPrime, k)).append(NEW_LINE);
			}
		}
		
		System.out.println(sb);	// 결과 출력
	}
	
	private static boolean[] getPrime() {
		boolean[] arr = new boolean[INF];
		Arrays.fill(arr, true);
		
		arr[0] = arr[1] = false;
		
		for(int i = 2; i < INF; i++) {
			for(int j = i + i; j < INF; j += i) {
				if(!arr[j]) continue;
				arr[j] = false;
			}
		}
		
		return arr;
	}
	
	private static int getRange(boolean[] arr, int num) {
		int idx1 = num;
		int idx2 = num;
		
		while(!(arr[idx1] && arr[idx2])) {		// 소수 사이의 범위가 확정되면 반복문 종료
			if(!arr[idx1]) idx1--;
			if(!arr[idx2]) idx2++;
		}
		
		return idx2 - idx1;
	}
}
