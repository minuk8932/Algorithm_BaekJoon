package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3566번: 대형 스크린
 *
 *	@see https://www.acmicpc.net/problem/3566/
 *
 */
public class Boj3566 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Monitor ordered = new Monitor(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		int n = Integer.parseInt(br.readLine());
		Monitor[] spare = new Monitor[n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			spare[i] = new Monitor(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(minCost(n, ordered, spare));
	}
	
	private static class Monitor{
		int widthResolution;
		int heightResolution;
		int width;
		int height;
		int cost;
		
		public Monitor(int widthResolution, int heightResolution, int width, int height) {
			this.widthResolution = widthResolution;
			this.heightResolution = heightResolution;
			this.width = width;
			this.height = height;
		}
		
		public Monitor(int widthResolution, int heightResolution, int width, int height, int cost) {
			this.widthResolution = widthResolution;
			this.heightResolution = heightResolution;
			this.width = width;
			this.height = height;
			this.cost = cost;
		}
	}
	
	private static int minCost(int N, Monitor target, Monitor[] comp) {
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			/**
			 *	해상도에 따른 모니터 갯수, 크기에 따른 모니터 갯수 최대, 총 갯수의 최소
			 *
			 */
			int	upright = getformat(target.heightResolution, comp[i].heightResolution, target.height, comp[i].height)
					* getformat(target.widthResolution, comp[i].widthResolution, target.width, comp[i].width);
			int intersect = getformat(target.heightResolution, comp[i].widthResolution, target.height, comp[i].width)
					* getformat(target.widthResolution, comp[i].heightResolution, target.width, comp[i].height);
			
			int cost = Math.min(upright, intersect) * comp[i].cost;
			if(min > cost) min = cost;
		}
		
		return min;
	}
	
	private static int getformat(int rTarget, int rComp, int sTarget, int sComp) {		// 갯수 반환
		return Math.max((rTarget + rComp - 1) / rComp, (sTarget + sComp - 1) / sComp);
	}
}
