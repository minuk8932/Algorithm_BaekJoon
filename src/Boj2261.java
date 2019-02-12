import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2261 {
	
	private static class Pair implements Comparable<Pair>{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			return this.x < p.x ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Pair[] pair = new Pair[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			pair[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(pair);
		
		System.out.println(getTheClosestDistance(N, pair));
	}
	
	private static int getTheClosestDistance(int n, Pair[] p) {
		Pair[] bound = new Pair[2];
		bound[0] = new Pair(p[0].x, p[0].y);
		bound[1] = new Pair(p[n - 1].x, p[n - 1].y);
		
		Pair[] mid = new Pair[2];
		mid[0] = new Pair(p[(n - 1) / 2].x, p[(n - 1) / 2].y);
		mid[1] = new Pair(p[n / 2].x, p[n / 2].y);
		
		int min = getDistance(mid);
		int lowerBound = binarySearch(bound[0], mid[0], min);
		int upperBound = binarySearch(mid[1], bound[1], min);
		
		return 0;
	}
	
	private static int binarySearch(Pair start, Pair end, int target) {
		
		
		return 0;
	}
	
	private static int getDistance(Pair[] p) {
		return (int) (Math.pow(p[0].x - p[1].x, 2) + Math.pow(p[0].y, p[1].y));
	}
}
