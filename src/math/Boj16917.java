package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16917번: 양념 반 후라이드 반
 *
 *	@see https://www.acmicpc.net/problem/16917/
 *
 */
public class Boj16917 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		
		System.out.println(getMinCost(A, B, C, X, Y));
	}
	
	private static int getMinCost(int src, int fry, int half, int srcCount, int fryCount) {
		int diff = Math.abs(srcCount - fryCount);
		
		// 양념 full 후라이드 full, 반반 full, 반반 + 나머지 양념 or 후라이드
		int[] res = {src * srcCount + fry * fryCount, half * Math.max(srcCount, fryCount) * 2,
				half * Math.min(srcCount, fryCount) * 2 + (srcCount > fryCount ? diff * src: diff * fry)};
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < res.length; i++) {
			if(res[i] < min) min = res[i];
		}
		
		return min;
	}
}
