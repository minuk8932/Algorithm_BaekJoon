package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1072번: 게임
 *
 *	@see https://www.acmicpc.net/problem/1072/
 *
 */
public class Boj1072 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long X = Integer.parseInt(st.nextToken());
		long Y = Integer.parseInt(st.nextToken());
		
		long winRate = getWinRate(X, Y);
		System.out.println(binarySearch(X, Y, winRate));
	}
	
	private static int binarySearch(long x, long y, long target) {
		int start = 1, end = (int) x, result = 0;
		if(getWinRate(x + x, y + x) == target) return -1;		// 승률이 안오르는 경우
		
		while(start <= end) {
			int mid = (start + end) / 2;
			long z = getWinRate(x + mid, y + mid);		// 승률이 오른건지 체크
			
			if(z > target) {
				end = mid - 1;
				result = mid;
			}
			else {
				start = mid + 1;
			}
		}
		
		return result;
	}
	
	private static long getWinRate(long x, long y) {
		return y * 100 / x;						// 소수화 방지
	}
}
