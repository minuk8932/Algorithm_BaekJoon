package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj11650 {
	public static final String SPACE = " ";
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Point[] pnt = new Point[N];
		
		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			pnt[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(pnt, Point.comparator);
		
		for(Point pnts : pnt){
			sb.append(pnts.x).append(SPACE).append(pnts.y).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	private static class Point{
		public int x;
		public int y;
		
		private Point (int x, int y){
			this.x = x;
			this.y = y;
		}
		private static Comparator<Point> comparator = new Comparator<Point>() {

			@Override
			public int compare(Point p1, Point p2) {
				if(p1.x < p2.x){
					return -1;
				} else if (p1.x == p2.x){
					if(p1.y < p2.y){
						return -1;
					} else {
						return 1;
					}
				} else {
					return 1;
				}
				
			}
		};
	}

}
