package convex_hull;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1708번: 블록껍질
 *
 *	@see https://www.acmicpc.net/problem/1708/
 *
 */
public class Boj1708 {
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

			if (y < min.y) {				// 가장 왼쪽 아래의 점을 min 변수에 저장
				min = new Point(x, y);
			}

			else if (y == min.y) {
				if (x < min.x) {
					min = new Point(x, y);
				}
			}

			pos[i] = new Point(x, y);
		}

		Arrays.sort(pos, Point.comparator);		// comparator를 이용한 정점 정렬
		convexHull(N, pos);					// 블록껍질 메소드 실행

		System.out.println(lifo.size());		// 스택의 길이 = 해당 정점의 수, 출력
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
	 *
	 */
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
	
				if (c > 0) {		// 좌측으로 꺽인 경우, 우선순위 부여
					return -1;
				}
				
				else if (c < 0) {		// 우측으로 꺽인 경우
					return 1;
				}
				
				else if (c == 0) {					// 직선상의 경우
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
	
	/**
	 * CCW(Counter Clock Wise) 메소드
	 * 
	 */
	private static long CCW(Point v1, Point v2, Point v3) {
		// 벡터의 외적 공식
		return (v1.x * v2.y + v2.x * v3.y + v3.x * v1.y) - (v3.x * v2.y + v2.x * v1.y + v1.x * v3.y);
	}
	
	/**
	 * 거리의 제곱값을 반환하는 메소드
	 * 
	 */
	private static long distancePow(Point p1, Point p2) {
		// 좌표내 두점 사이의 거리 공식
		return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
	}
	
	/**
	 * 블록껍질 메소드
	 * 
	 */
	private static void convexHull(int N, Point[] p) {
		lifo = new Stack<>();
		lifo.push(new Point(p[0].x, p[0].y));		// 0, 1번 정점을 스택에 담아줌
		lifo.push(new Point(p[1].x, p[1].y));

		for (int idx = 2; idx < N; idx++) {				// 2번 정점부터 시작
			Point next = new Point(p[idx].x, p[idx].y);		// 다음 정점을 설정 후

			while (lifo.size() >= 2) {			// 스택에 정점이 2개이상 들어있는 경우에
				Point second = lifo.pop();		// 중앙에 해당할 점은 뽑아오고, 시작 정점은 참조만 하여 변수에 저장
				Point first = lifo.peek();

				if (CCW(first, second, next) > 0) {		// 시작, 중앙, 다음 정점을 통한 CCW를 통해 좌회전인지 확인 후
					lifo.push(second);				// 중앙 정점을 다시 스택에 담아줌
					break;
				}
			}

			lifo.push(next);			// second 정점이 좌회전이 아니었다면, 다음 정점을 스택에 담아줌 (스택엔 현재 first와 next가 순서대로 담겨져 있음)
		}
	}
}
