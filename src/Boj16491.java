import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj16491 {
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	private static ArrayList<String> seq = new ArrayList<>();
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
		
		Point[] robot = new Point[N + 1];
		Point[] shelter = new Point[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			robot[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			shelter[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(process(N, robot, shelter));
	}
	
	private static StringBuilder process(int n, Point[] robo, Point[] shel) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < n + 1; i++) {
			isVisited = new boolean[n + 1];
			backTracking(n, i, 0, i + "");
		}
		
		for(String str: seq) {
			StringTokenizer st = new StringTokenizer(str);
			int[] tmp = new int[n + 1];
			
			for(int i = 1; i < n + 1; i++) {
				tmp[i] = Integer.parseInt(st.nextToken());
			}
			
			boolean flag = false;
			
			for(int i = 1; i < n + 1; i++) {
				if(flag) break;
				for(int j = i + 1; j < n + 1; j++) {
					flag = isIntersect(robo[i], shel[tmp[i]], robo[j], shel[tmp[j]]);
				}
			}
				
			if(!flag) {
				for(int idx = 1; idx < tmp.length; idx++) {
					sb.append(tmp[idx]).append(NEW_LINE);
				}
					
				break;
			}
		}
		
		return sb;
	}
	
	private static void backTracking(int n, int current, int depth, String str) {
		if(depth == n - 1) {
			seq.add(str);
			return;
		}
		
		if(isVisited[current]) return;
		isVisited[current] = true;
		
		for(int next = 1; next < n + 1; next++) {
			if(isVisited[next]) continue;
			
			backTracking(n, next, depth + 1, str + SPACE + next);
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
