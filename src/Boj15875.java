import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj15875 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;

	private static ArrayList<Point> pq = new ArrayList<>();
	private static Point[][] map;
	private static int[] parent;
	private static int H, W;

	private static class Point implements Comparable<Point>{
		int h;
		int check;
		Point p;

		public Point(int h, int check) {
			this.h = h;
			this.check = check;
		}

		@Override
		public int compareTo(Point p) {
			return this.h - p.h;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		init();

		for(int i = 1; i <= H; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 1; j <= W; j++) {
				map[i][j].h = Integer.parseInt(st.nextToken());
				map[i][j].check++;
				pq.add(map[i][j]);
			}
		}

		System.out.println(search());
	}

	private static int search() {
		Collections.sort(pq);
		int len = pq.size();
		int result = 0;

		for(int i = 0; i < len;) {
			int current = i;

			while(pq.get(current).h == pq.get(current).h){
				for(final int[] DIRECTION: DIRECTIONS){
//					merge(pq.get(i).p, pq.get(i).p + DIRECTION[COL] + DIRECTION[ROW] * size);
				}

				i++;
			}

			for(int j = current; j < i; j++){
//				result = Math.max(result, find(pq.get(j).p).count);
			}
		}

		return 0;
	}

	private static void merge(int x, int y) {
//		if(x.h < y.h) return;

		x = find(x);
		y = find(y);

		if(x == y) return;

//		y.p = x;
//		x.check *= y.check;

		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}

	private static int find(int x) {
		if(parent[x] < 0) return x;
		return  parent[x] = find(parent[x]);
	}

	private static void init(){
		parent = new int[(H + 2) * (W + 2)];
		map = new Point[H + 2][W + 2];

		for(int i = 0; i < H + 2; i++) {
			for(int j = 0; j < W + 2; j++){
				map[i][j] = new Point(0, 0);
				map[i][j].p = map[i][j];
				parent[i * map[i].length + j] = -1;
			}
		}
	}
}