package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 4577번: 소코반
 *
 *	@see https://www.acmicpc.net/problem/4577/
 *
 */
public class Boj4577 {
	private static final String TERMINATE = "0 0";
	private static final String SUCCESS = "complete";
	private static final String FAIL = "incomplete";
	private static final String GAME = "Game ";
	private static final String COLON = ": ";
	private static final String NEW_LINE = "\n";
	
	private static char[][] map;
	private static final char CHARACTER_PLUS = 'W';
	private static final char CHARACTER = 'w';
	private static final char BOX_PLUS = 'B';
	private static final char BOX = 'b';
	private static final char BLOCK = '#';
	private static final char EMPTY = '.';
	private static final char DESTINATION = '+';
	private static final char LEFT = 'L';
	private static final char RIGHT = 'R';
	private static final char UP = 'U';
	private static final char DOWN = 'D';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int D = 0;
	private static final int R = 1;
	private static final int U = 2;
	private static final int L = 3;
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int loop = 1;
		
		while(true) {
			String cmd = br.readLine();
			if(cmd.equals(TERMINATE)) break;			// "0 0"이 입력으로 들어온 경우 반복문 종료
			
			StringTokenizer st = new StringTokenizer(cmd);
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			map = new char[R][C];
			Point start = input(R, C, br);						// 입력 메소드를 통해 캐릭터 시작점과 맵을 설정함
			LinkedList<Character> move = new LinkedList<>();
			char[] moveChase = br.readLine().toCharArray();
			for(int i = 0; i < moveChase.length; i++) {			// 연결리스트에 캐릭터가 움직이는 순서를 저장
				move.add(moveChase[i]);
			}
			
			bfs(start, R, C, move);			// 너비 우선 탐색을 통한 동작 시작
			
			sb.append(GAME).append(loop++).append(COLON).append(isComplete(R, C) ? SUCCESS : FAIL).append(NEW_LINE);	// 성공 실패 여부를 버퍼에 저장
			sb.append(mapStatus(R, C));			// 맵 상태 메소드를 통해 마지막의 맵 상태를 버퍼에 저장
		}
		
		System.out.println(sb);		// 결과 값 한번에 출력
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	/**
	 * 입력 메소드
	 * @param r: 세로
	 * @param c: 가로
	 * @param br: 입력
	 * @return: 캐릭터 출발 위치
	 */
	private static Point input(int r, int c, BufferedReader br) throws Exception {
		int row = 0, col = 0;
		
		for(int i = 0; i < r; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < c; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == CHARACTER || map[i][j] == CHARACTER_PLUS) {		// 캐릭터가 도착지에서 시작하는지 출발지에서 시작하는지 체크
					row = i;
					col = j;
				}
			}
		}
		
		return new Point(row, col);
	}
	
	/**
	 * 너비 우선 탐색 메소드
	 * @param s: 캐릭터 시작 지점
	 * @param r: 세로
	 * @param c: 가로
	 * @param m: 움직임
	 */
	private static void bfs(Point s, int r, int c, LinkedList<Character> m) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(s.row, s.col));
		
		while(!m.isEmpty()) {
			Point current = q.poll();
			char moving = m.removeFirst();
			
			if(isComplete(r, c)) return;		// 상자 이동이 완료되었으면 메소드 종료
			
			int nextRowMove = 0, nextColMove = 0;
			
			switch (moving) {					// 현재 움직일 방향에 따라 DIRECTION 값을 설정
			case LEFT:
				nextRowMove = DIRECTIONS[L][ROW];
				nextColMove = DIRECTIONS[L][COL];
				break;
				
			case RIGHT:
				nextRowMove = DIRECTIONS[R][ROW];
				nextColMove = DIRECTIONS[R][COL];
				break;
				
			case UP:
				nextRowMove = DIRECTIONS[U][ROW];
				nextColMove = DIRECTIONS[U][COL];
				break;
				
			case DOWN:
				nextRowMove = DIRECTIONS[D][ROW];
				nextColMove = DIRECTIONS[D][COL];
				break;
			}
			
			int nextRow = current.row + nextRowMove;
			int nextCol = current.col + nextColMove;
			
			// 다음 움직일 경로가 이동 불가능한 경우
			if(nextRow < 0 || nextRow >= r || nextCol < 0 || nextCol >= c || map[nextRow][nextCol] == BLOCK) {
				q.offer(new Point(current.row, current.col));
				continue;
			}
			
			if(map[nextRow][nextCol] == EMPTY) {						// 다음 경로가 빈 경우
				if(map[current.row][current.col] == CHARACTER_PLUS) {	// 현재 캐릭터가 W인 경우
					map[current.row][current.col] = DESTINATION;		// 현위치는 +로 변경
				}
				else {
					map[current.row][current.col] = EMPTY;				// 아닌 경우는 .로 변경
				}
				
				map[nextRow][nextCol] = CHARACTER;						// 다음 위치는 비어있으므로 w
				q.offer(new Point(nextRow, nextCol));
			}
			
			else if(map[nextRow][nextCol] == DESTINATION) {				// 다음 경로가 +인 경우
				if(map[current.row][current.col] == CHARACTER_PLUS) {	// 현 경로가 W인 경우
					map[current.row][current.col] = DESTINATION;		// 현 경로를 +로 비워줌
				}
				else if(map[current.row][current.col] == CHARACTER){	// 현 경로가 w인 경우
					map[current.row][current.col] = EMPTY;				// 현 경로를 .로 비워줌
				}
				
				map[nextRow][nextCol] = CHARACTER_PLUS;						// 다음 경로에 W를 담아줌
				q.offer(new Point(nextRow, nextCol));
			}
			
			else if(map[nextRow][nextCol] == BOX_PLUS || map[nextRow][nextCol] == BOX) {	// 다음 경로에 박스가 존재하는 경우
				int afterRow = nextRow + nextRowMove;		// 박스 뒤 경로까지 구함
				int afterCol = nextCol + nextColMove;
				
				// 박스를 밀어낼 수 없는 경우
				if(map[afterRow][afterCol] == BOX_PLUS || map[afterRow][afterCol] == BLOCK || map[afterRow][afterCol] == BOX) {
					q.offer(new Point(current.row, current.col));
					continue;
				}
				else {
					if(map[afterRow][afterCol] == DESTINATION) {		// 박스 뒤가 목적지인 경우
						map[afterRow][afterCol] = BOX_PLUS;				// 박스 뒤를 B로 채우고
						
						if(map[nextRow][nextCol] == BOX_PLUS) {			// 다음 경로에 있던 박스가 B인 경우
							map[nextRow][nextCol] = CHARACTER_PLUS;		// 다음 경로에 W로 채움
							
							if(map[current.row][current.col] == CHARACTER_PLUS) {	// 현 경로에 W가 있었던 경우
								map[current.row][current.col] = DESTINATION;		// 현 경로를 +로 비움
							}
							else if(map[current.row][current.col] == CHARACTER){	// 현 경로에 w가 있던 경우
								map[current.row][current.col] = EMPTY;				// 현 경로를 .로 비움
							}
						}
						else if(map[nextRow][nextCol] == BOX) {			// 다음 경로에 있던 박스가 b인 경우
							map[nextRow][nextCol] = CHARACTER;			// 다음 경로에 w로 채움
							
							if(map[current.row][current.col] == CHARACTER_PLUS) {	// 아래는 위와 동일
								map[current.row][current.col] = DESTINATION;
							}
							else if(map[current.row][current.col] == CHARACTER){
								map[current.row][current.col] = EMPTY;
							}
						}
					}
					else if(map[afterRow][afterCol] == EMPTY) {			// 박스 뒤가 빈 경우
						map[afterRow][afterCol] = BOX;					// 박스 뒤를 b로 채우고
						
						if(map[nextRow][nextCol] == BOX_PLUS) {			// 아래는 위와 같음
							map[nextRow][nextCol] = CHARACTER_PLUS;
							
							if(map[current.row][current.col] == CHARACTER_PLUS) {
								map[current.row][current.col] = DESTINATION;
							}
							else if(map[current.row][current.col] == CHARACTER){
								map[current.row][current.col] = EMPTY;
							}
						}
						else if(map[nextRow][nextCol] == BOX) {
							map[nextRow][nextCol] = CHARACTER;
							
							if(map[current.row][current.col] == CHARACTER_PLUS) {
								map[current.row][current.col] = DESTINATION;
							}
							else if(map[current.row][current.col] == CHARACTER){
								map[current.row][current.col] = EMPTY;
							}
						}
					}
				}
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
	}
	
	/**
	 * 미션 성공 여부 메소드
	 * 
	 */
	private static boolean isComplete(int r, int c) {
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(map[i][j] == BOX) return false;		// 맵에 도착못한 박스가 하나라도 존재할 시 실패
			}
		}
		
		return true;
	}
	
	/**
	 * 맵 상태 출력 메소드
	 * 
	 */
	private static String mapStatus(int r, int c) {
		StringBuilder getMap = new StringBuilder();
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {		// 맵 상태를 버퍼에 담고
				getMap.append(map[i][j]);
			}
			
			getMap.append(NEW_LINE);
		}
		
		
		return getMap.toString();				// 문자열로 반환
	}
}
