package counter_clock_wise;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6493번: 교차
 *
 *	@see https://www.acmicpc.net/problem/6493/
 *
 */
public class Boj6493 {
	private static final String NEW_LINE = "\n";
	private static final String TRUE = "T";
	private static final String FALSE = "F";
	
	private static class Pair{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Pair[] line = new Pair[2];
			line[0] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			line[1] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			Pair[] sqr = new Pair[4];
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			sqr[0] = new Pair(x1, y1);
			sqr[1] = new Pair(x1, y2);
			sqr[2] = new Pair(x2, y1);
			sqr[3] = new Pair(x2, y2);
			
			boolean isTrue = isCrossed(line, sqr[0], sqr[1]) || isCrossed(line, sqr[1], sqr[3]) 
					|| isCrossed(line, sqr[3], sqr[2]) || isCrossed(line, sqr[2], sqr[0]);
			
			if(!isTrue) {
				if(x1 > x2) {
					int tmp = x1;
					x1 = x2;
					x2 = tmp;
				}
				
				if(y1 > y2) {
					int tmp = y1;
					y1 = y2;
					y2 = tmp;
				}
				// 직선에 양끝 점 중 값이 작은 점을 기준으로 해당 점이 사각형 내에 포함되는지 확인 
				isTrue = x1 < line[0].x && x2 > line[0].x && y1 < line[0].y && y2 > line[0].y;
			}
			
			sb.append(isTrue ? TRUE : FALSE).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static boolean isCrossed(Pair[] l,Pair s1, Pair s2) {
		// 사각형 꼭지점과 만나는 경우
		boolean hasSameThing = isSame(l[0], s1) || isSame(l[0], s2) || isSame(l[1], s1) || isSame(l[1], s2);
		if(hasSameThing) return true;
		
		int product1 = ccw(l[0], l[1], s1) * ccw(l[0], l[1], s2);		// 외적
		int product2 = ccw(s1, s2, l[0]) * ccw(s1, s2, l[1]);
		
		if(product1 == 0 && product2 == 0) {		// 둘다 0이면 평면상에서 대각선이 아닌 선분
			if(compare(l[0], l[1])) {
				Pair tmp = l[0];
				l[0] = l[1];
				l[1] = tmp;
			}
			
			if(compare(s1, s2)) {
				Pair tmp = s1;
				s1 = s2;
				s2 = tmp;
			}
			// 해당 범위에 하나라도 속하지 않는다면 교차하지 못함
			return (l[0].x < s1.x && l[1].x < s1.x ? false : true) && (l[0].y < s1.y && l[1].y < s1.y ? false : true) &&
					(l[0].x > s1.x && l[0].x > s2.x ? false : true) && (l[0].y > s1.y && l[0].y > s2.y ? false : true);
		}
		
		return product1 <= 0 && product2 <= 0;
	}
	
	private static int ccw(Pair p1, Pair p2, Pair p3) {
		long product = (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p1.y * p2.x + p2.y * p3.x + p3.y * p1.x);
		return product == 0 ? 0 : product > 0 ? 1 : -1;
	}
	
	private static boolean isSame(Pair p1, Pair p2) {
		return p1.x != p2.x ? false : p1.y == p2.y;
	}
	
	private static boolean compare(Pair p1, Pair p2) {
		return p1.x != p2.x ? p1.x > p2.x : p1.y > p2.y;
	}
}
