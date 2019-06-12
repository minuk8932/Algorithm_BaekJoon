package counter_clock_wise;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1027번: 고층 빌딩
 *
 *	@see https://www.acmicpc.net/problem/1027/
 *
 */
public class Boj1027 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[] buildings = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			buildings[i] = Long.parseLong(st.nextToken());
		}
		
		System.out.println(search(N, buildings));
	}
	
	private static int search(int n, long[] arr) {
		int max = 0;
		
		for(int target = 0; target < n; target++) {
			int total = getSight(n,target, arr);			// target 빌딩에서 볼 수 있는 빌딩의 갯수
			if(total > max) max = total;
		}
		
		return max;
	}
	
	private static boolean ccw(long x1, long y1, long x2, long y2, long x3, long y3, boolean flag) {
		long type = (x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3);
		if(flag) return type > 0 ? true: false;
		else return type < 0 ? true: false;
	}
	
	private static int getSight(int n, int start, long[] arr) {
		int count = 0;
		
		for(int via = 0; via < start; via++) {					// 좌측에 보이는 빌딩
			boolean flag = true;
			
			for(int end = via + 1; end < start; end++) {
				flag = ccw(start, arr[start], via, arr[via], end, arr[end], true);		// 직선이 위로 솟는 경우(반시계)
				if(!flag) break;
			}
			
			if(flag) count++;
		}
		
		for(int via = n - 1; via > start; via--) {				// 우측에 보이는 빌딩
			boolean flag = true;
			
			for(int end = via - 1; end > start; end--) {
				flag = ccw(start, arr[start], via, arr[via], end, arr[end], false);		// 직선이 위로 솟는 경우(시계)
				if(!flag) break;
			}

			if(flag) count++;
		}
		
		return count;
	}
}
