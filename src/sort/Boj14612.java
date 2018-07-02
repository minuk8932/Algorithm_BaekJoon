package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14612번: 김식당
 *
 *	@see https://www.acmicpc.net/problem/14612/
 *
 */
public class Boj14612 {
	private static final String O = "order";
	private static final String S = "sort";
	private static final String C = "complete";
	private static final String END = "sleep";
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	private static final int INF = 1_001;
	
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Point[] p = new Point[INF];
		for(int i = 0; i < INF; i++) {		// Point 배열 초기화 INF: 0으로하면 정렬시 입력된 값들이 앞으로 당겨지는 현상이 발생.
			p[i] = new Point(INF, INF);
		}
		
		int idx = 1;

		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			int num = 0;
			int time = 0;
			
			if(command.equals(O)) {							// order의 경우
				num = Integer.parseInt(st.nextToken());
				time = Integer.parseInt(st.nextToken());
			}
			else if(command.equals(C)) {					// complete의 경우
				num = Integer.parseInt(st.nextToken());
			}

			switch (command) {
			case O:
				add(p, idx, num, time);		// 주문 입력
				
				idx++;
				break;

			case S:							// 입력된 값들을 정렬
				if(idx <= 2) break;
				
				sort(p);
				break;

			case C:							// 완료된 주문을 제거
				complete(p, num);
				break;
			}

			print(p);						// 각 경우마다 존재하는 주문을 버퍼에 담음
		}

		System.out.println(sb.toString());	// 결과값 한번에 출력
	}
	
	/**
	 * 주문 내용 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Point{
		int table;
		int time;

		public Point(int table, int time) {
			this.table = table;
			this.time = time;
		}
		
		private static Comparator<Point> comparator = new Comparator<Point>() {

			@Override
			public int compare(Point p1, Point p2) {	// 내부 값들을 비교
				if (p1.time < p2.time) {			// 주문시간이 빠른것을 더 앞으로
					return -1;
				} 
				
				else if (p1.time > p2.time) {
					return 1;
				} 
				
				else {								// 주문시간이 같다면
					if(p1.table < p2.table) {		// 테이블 번호가 빠른것을 더 앞으로
						return -1;
					}
					else if(p1.table > p2.table){
						return 1;
					}
					else {							// 테이블 번호가 서로 같은 경우
						return 0;
					}
				}
			}
		};
	}
	
	private static void print(Point[] p) {			// 출력 메서드
		int cnt = 0;
		
		for(int i = 0; i < p.length; i++) {
			if(p[i].table == INF && p[i].time == INF) cnt++;	// 출력할 내용이 없는 경우 체크
		}
		
		if(cnt == p.length) {						// 출력할 내용이 없을 땐 sleep을 버퍼에 담음
			sb.append(END).append(NEW_LINE);
			return;
		}
		
		for(int i = 0; i < p.length; i++) {					// 출력할 내용이 있는 경우 INF 값를 제외하고 버퍼에 담음
			if(p[i].table == INF && p[i].time == INF) continue;
			
			sb.append(p[i].table).append(SPACE);
		}
		
		sb.append(NEW_LINE);
	}
	
	private static void add(Point[] p, int idx, int tNum, int time) {	// 주문이 들어오면 배열에 추가하는 메서드
		p[idx].table = tNum;
		p[idx].time = time;
	}
	
	private static void sort(Point[] p) {				// 주문을 문제 기준에 따라 정렬하는 메서드
		Arrays.sort(p, Point.comparator);
	}

	private static void complete(Point[] p, int tNum) {		// 완료된 주문을 제거하는 메서드
		for(int i = 0; i < p.length; i++) {
			if(p[i].table == tNum) {
				p[i] = new Point(INF, INF);
				break;
			}
		}
	}
}
