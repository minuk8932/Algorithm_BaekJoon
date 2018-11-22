package convex_hull;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6850번: Cows
 *
 *	@see https://www.acmicpc.net/problem/6850/
 *
 */
public class Boj6850 {
	private static Stack<Point> lifo = null;

	private static final int INF = 40_001;
	private static Point min = new Point(INF, INF);

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Point[] pos = new Point[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if (y < min.y) {				// 가장 왼쪽 아래의 점(시작 정점)을 min 변수에 저장
				min = new Point(x, y);
			}

			else if (y == min.y) {
				if (x < min.x) {
					min = new Point(x, y);
				}
			}

			pos[i] = new Point(x, y);
		}

		Arrays.sort(pos, Point.comparator);
		convexHull(N, pos);
		
		System.out.println(getArea());
	}
	
	private static class Point {
		long x;
		long y;

		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
		
		private static Comparator<Point> comparator = new Comparator<Point>() {

			@Override
			public int compare(Point p1, Point p2) {
				long c = CCW(min, p1, p2);			// 최 좌측 하단 점과, 정점 배열내의 점을 2개씩 뽑아 CCW 실행
	
				if (c > 0) {			// 좌측
					return -1;
				}
				
				else if (c < 0) {		// 우측
					return 1;
				}
				
				else if (c == 0) {					// 직선상
					long a = distancePow(min, p1);
					long b = distancePow(min, p2);
					
					if (a < b) return -1;			// 거리가 가까운 정점부터 우선순위 부여
					else if (a > b) return 1;
					else if (a == b) return 0;
				}
				
				return 0;
			}
		};
	}
	
	private static long CCW(Point v1, Point v2, Point v3) {
		// 벡터의 외적 공식
		return (v1.x * v2.y + v2.x * v3.y + v3.x * v1.y) - (v3.x * v2.y + v2.x * v1.y + v1.x * v3.y);
	}
	
	private static long distancePow(Point p1, Point p2) {
		// 좌표내 두점 사이의 거리 공식
		return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
	}
	
	/**
	 * 볼록껍질 메소드
	 * 스택에 CCW를 통해 좌회전이면서 거리가 가장 가까운 점 부터 스택에 저장
	 * 
	 */
	private static void convexHull(int N, Point[] p) {
		lifo = new Stack<>();
		lifo.push(new Point(p[0].x, p[0].y));		// 0, 1번 정점을 스택에 담아줌
		lifo.push(new Point(p[1].x, p[1].y));

		for (int idx = 2; idx < N; idx++) {
			Point next = new Point(p[idx].x, p[idx].y);		// 다음 정점

			while (lifo.size() >= 2) {
				Point second = lifo.pop();		// 중앙 정점
				Point first = lifo.peek();		// 시작 정점

				if (CCW(first, second, next) > 0) {
					lifo.push(second);
					break;
				}
			}

			lifo.push(next);			// second 정점이 좌회전이 아니었다면, 다음 정점을 스택에 담아줌 (스택엔 현재 first와 next가 순서대로 담겨져 있음)
		}
	}
	
	private static long getArea(){
		long res = 0;
		
		ArrayList<Point> arr = new ArrayList<>();
		
		while(!lifo.isEmpty()) {
			Point p = lifo.pop();
			arr.add(new Point(p.x, p.y));
		}
		
		int size = arr.size();
		// 벡터 외적: 다각형 넓이 구하기, 스택에서 우회전 순서로 뽑아오기떄문에 식을 뒤집어줌
		for(int i = 0; i < size; i++) {			
			res += (getArrayValue(arr, i + 1, i, size) - getArrayValue(arr, i, i + 1, size));
		}
		
		return res / 100;		// 문제 조건과 다각형 넓이 구하는 공식에 따라 100 나누기
	}
	
	private static long getArrayValue(ArrayList<Point> p, int idx, int postIdx, int s) {
		long x = p.get(idx % s).x;
		long y = p.get(postIdx % s).y;
		
		return x * y;
	}
}
