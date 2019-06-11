package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2018번: 수들의 합 5
 *
 *	@see https://www.acmicpc.net/problem/2018/
 *
 */
public class Boj2018 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(getCount(N));
	}
	
	private static int getCount(int n) {
		int count = 1;
		
		for(long target = 1; target < n; target++) {
			long start = target + 1, end = n;			// 시작 수
			
			while(start <= end) {
				long mid = (start + end) / 2;
				
				long total = (target + mid) * (mid - target + 1) / 2;		// 시작수 ~ 끝수 까지의 합
				
				if(total > n) {
					end = mid - 1;
				}
				else if(total < n) {
					start = mid + 1;
				}
				else {					// 같은 수를 갖는경우 갯수 증가 후 이분탐색 종료
					count++;
					break;
				}
			}
		}
		
		return count;
	}
}
