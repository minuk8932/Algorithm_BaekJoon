package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 15916번: 가희는 그래플러야!
 *
 *	@see https://www.acmicpc.net/problem/15916/
 *
 */
public class Boj15916 {
	private static class Coordinate{
		long y1;
		long y2;
		
		public Coordinate(long y1, long y2) {
			this.y1 = y1;
			this.y2 = y2;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		long[] arr = new long[n+ 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i < n + 1; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		long k = Long.parseLong(br.readLine());
		System.out.println(intersection(n, arr, k) ? "T": "F");
	}
	
	private static boolean intersection(int n, long[] arr, long tilt) {
		Coordinate[] target = new Coordinate[2];
		
		if(n == 1) {									// 1개일때 두점이 동일하면, 겹치는 선
			return tilt == arr[1] ? true: false;
		}
		
		for(int i = 2; i < n + 1; i++) {
			target[0] = new Coordinate(arr[i - 1], tilt * (i - 1));
			target[1] = new Coordinate(arr[i], tilt * i);
			
			if(isCrossed(target)) return true;			// 교차하는 경우
		}
		
		return false;
	}
	
	private static boolean isCrossed(Coordinate[] t) {
		return (t[0].y1 - t[0].y2 <= 0 && t[1].y1 - t[1].y2 >= 0) ||
				(t[0].y1 - t[0].y2 >= 0 && t[1].y1 - t[1].y2 <= 0) ? true: false;
	}
}
