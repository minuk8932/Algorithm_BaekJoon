package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14719번: 빗물
 *
 *	@see https://www.acmicpc.net/problem/14719/
 *
 */
public class Boj14719 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] map = new int[W];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < W; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(rainDrop(H, W, map));
	}
	
	private static int rainDrop(int h, int w, int[] map) {
		int result = 0;
		
		for(int i = 0; i < w; i++) {
			int target = map[i];
			int left = i - 1;
			int right = i + 1;
			
			int maxL = 0, maxR = 0;
			while(true) {
				if(left < 0) break;
				maxL = Math.max(maxL, map[left--]);
			}
			
			while(true) {
				if(right >= w) break;
				maxR = Math.max(maxR, map[right++]);
			}
			
			if(maxR == 0 || maxL == 0 || maxR <= target || maxL <= target) continue;	// 빗물이 찰 수 없는 경우
			result += Math.min(maxR, maxL) - target;			// 각 열마다 하나씩 빗물을 채워줌
		}
		
		return result;
	}
}
