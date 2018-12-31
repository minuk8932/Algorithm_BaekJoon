package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1063번: 킹
 *
 *	@see https://www.acmicpc.net/problem/1063/
 *
 */
public class Boj1063 {	
	private static Point king, stone;
	private static HashMap<String, Point> hm = new HashMap<>();
	
	private static final char ONE = 'B';
	private static final char SPACE = ' ';
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String K = st.nextToken();
		String S = st.nextToken();
		int N = Integer.parseInt(st.nextToken());
		
		king = new Point(K.charAt(0), K.charAt(1) - '0');
		stone = new Point(S.charAt(0), S.charAt(1) - '0');
		
		directionInit();
		
		while(N-- > 0) {
			String move = br.readLine();
			goKing(move);
		}
		
		System.out.println(getRes());		// 킹과 돌의 위치 출력
	}
	
	private static class Point{
		char alpha;
		int num;
		
		public Point(char alpha, int num) {
			this.alpha = alpha;
			this.num = num;
		}
	}
	
	private static void directionInit() {		// 움직임에 대한 값 정보 초기화
		hm.put("R", new Point('C', 0));
		hm.put("L", new Point('A', 0));
		hm.put("B", new Point(' ', -1));
		hm.put("T", new Point(' ', 1));
		hm.put("RT", new Point('C', 1));
		hm.put("LT", new Point('A', 1));
		hm.put("RB", new Point('C', -1));
		hm.put("LB", new Point('A', -1));
	}
	
	private static void goKing(String m) {
		Point value = hm.get(m);
		int alpha = 0;
		int num = 0;
		
		if(value.alpha != SPACE) alpha = value.alpha - ONE;		// 킹 움직임
		num = value.num;
		
		Point validKing = updatePositionValid(king, alpha, num);	// 킹이 범위를 벗어나는가?
		if(validKing == null) return;
		
		char next = validKing.alpha;
		int nextNum = validKing.num;
		
		if(isStoneNext(next, nextNum)) {								// 킹 다음 위치에 돌이 존재하는가?
			Point validStone = updatePositionValid(stone, alpha, num);	// 돌이 범위를 벗어 나는가?
			if(validStone == null) return;
			
			stone = new Point(validStone.alpha, validStone.num);
		}
		
		king = new Point(next, nextNum);
	}
	
	private static boolean isStoneNext(char alpha, int num) {
		if(stone.alpha == alpha && stone.num == num) return true;
		else return false;
	}
	
	private static boolean isOut(char alpha, int num) {
		if(!(alpha >= 'A' && alpha <= 'H') || !(num > 0 && num < 9)) return true;
		else return false;
	}
	
	private static Point updatePositionValid(Point p, int alpha, int num) {
		char nextAlpha = (char) (p.alpha + alpha);
		int nextNum = p.num + num;
		
		if(isOut(nextAlpha, nextNum)) return null;
		return new Point(nextAlpha, nextNum);
	}
	
	private static StringBuilder getRes() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(king.alpha).append(king.num).append(NEW_LINE).
		append(stone.alpha).append(stone.num);
		
		return sb;
	}
}
