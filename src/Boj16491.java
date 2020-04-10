import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj16491 {
	private static final String NEW_LINE = "\n";
	private static long INF;
	
	private static ArrayList<Long> seq = new ArrayList<>();
	private static boolean[] isVisited;
	
	private static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		INF = (long) Math.pow(10, N - 1);
		
		Point[] robot = new Point[N];
		Point[] shelter = new Point[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			robot[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			shelter[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(process(N, robot, shelter));
	}
	
	private static StringBuilder process(int n, Point[] robo, Point[] shel) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			isVisited = new boolean[n];
			backTracking(n, i, 0, i);
		}
		
		for(long s: seq) {
			int[] tmp = new int[n];
			int loop = n;

			if(s < INF){
				loop--;
				tmp[n - 1] = 0;
			}
			
			for(int i = 0; i < loop; i++) {
				tmp[i] = (int) (s % 10);
				s /= 10;
			}
			
			boolean flag = false;
			
			for(int i = 0; i < n; i++) {
				if(flag) break;
				for(int j = i + 1; j < n; j++) {
					flag = isIntersect(robo[i], shel[tmp[i]], robo[j], shel[tmp[j]]);
				}
			}
				
			if(!flag) {
				for(int idx = 0; idx < tmp.length; idx++) {
					sb.append(tmp[idx] + 1).append(NEW_LINE);
				}
					
				break;
			}
		}
		
		return sb;
	}
	
	private static void backTracking(int n, int current, int depth, long value) {
		if(depth == n - 1) {
			seq.add(value);
			return;
		}
		
		if(isVisited[current]) return;
		isVisited[current] = true;
		
		for(int next = 0; next < n; next++) {
			if(isVisited[next]) continue;
			
			backTracking(n, next, depth + 1, value * 10 + next);
			isVisited[next] = false;
		}
	}
	
	private static boolean isIntersect(Point a, Point b, Point c, Point d) {
		int[] value = {ccw(a, b, c) * ccw(a, b, d), ccw(c, d, a) * ccw(c, d, b)};
		
		if(value[0] <= 0 && value[1] <= 0) return true;
		return false;
	}
	
	private static int ccw(Point p1, Point p2, Point p3) {
		int cost = (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p3.y) * (p3.x - p1.x);
		
		if(cost < 0) return -1;
		else if(cost > 0) return 1;
		else return 0;
	}
}
