package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2174번: 로봇 시뮬레이션
 *
 *	@see https://www.acmicpc.net/problem/2174/
 *
 */
public class Boj2174 {
	private static int[][] map = null;
	private static int[][] rMove = null;
	private static int oppo = 0;
	private static boolean isBreak = false;
	private static String[] campus = null;
	
	private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final int ERROR_INTERACTED = -1;
	private static final int ERROR_BLOCKED = -2;
	private static final int COMPLETE = 1;
	
	private static final String R = "Robot ";
	private static final String WALL_CRASH = " crashes into the wall";
	private static final String ROBOT_CRASH = " crashes into robot ";
	private static final String POSSIBLE = "OK";
	private static final String[] BEARING = {"N", "E", "S", "W"};
	private static final String FORWARD = "F";
	private static final String TURN_LEFT = "L";
	private static final String TURN_RIGHT = "R";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		map = new int[n + 1][m + 1];
		
		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int robot = 1;
		
		rMove = new int[A + 1][2];
		campus = new String[A + 1];
		while(A-- > 0) {
			st = new StringTokenizer(br.readLine());
			int colPos = Integer.parseInt(st.nextToken());
			int rowPos = n - Integer.parseInt(st.nextToken()) + 1;	// 문제의 조건에서 원래 배열 모양과 동등하게 바꾸어 입력
			campus[robot] = st.nextToken();		// 해당 로봇이 바라보는 방향을 배열에 담아줌
			map[rowPos][colPos] = robot;		// 로봇을 숫자로 맵에 위치를 지정
						
			for(int i = 0; i < BEARING.length; i++) {	// 로봇이 바라보는 방향에 따라 움직일 방향을 정해줌
				if(BEARING[i].equals(campus[robot])) {
					rMove[robot][0] = DIRECTIONS[i][ROW];
					rMove[robot][1] = DIRECTIONS[i][COL];
				}
			}
			
			robot++;
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(B-- > 0) {
			st = new StringTokenizer(br.readLine());
			int roboNum = Integer.parseInt(st.nextToken());
			String command = st.nextToken();
			int go = Integer.parseInt(st.nextToken());
			int res = COMPLETE;
			
			switch (command) {					// 명령에 따라 수행
			case FORWARD:							// 전진
				res = forward(n, m, roboNum, go);
				break;
				
			case TURN_RIGHT:						// 우회전
				turnR(roboNum, go);
				break;
				
			case TURN_LEFT:							// 좌회전
				turnL(roboNum, go);
				break;
			}
			
			if(res != COMPLETE) {
				if(res == ERROR_BLOCKED) {				// 벽에 부딪힌 경우
					sb.append(R).append(roboNum).append(WALL_CRASH);
				}
				else if(res == ERROR_INTERACTED) {		// 임의의 어떤 로봇과 부딪힌 경우
					sb.append(R).append(roboNum).append(ROBOT_CRASH).append(oppo);
				}
				
				isBreak = true;						// 정상 종료한 경우
				
				break;
			}
		}
		
		if(!isBreak) sb.append(POSSIBLE);			// 세 조건 중 하나를 버퍼에 담고
		
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
	
	/**
	 * 
	 * 직진 메소드
	 */
	private static int forward(int N, int M, int num, int go) {
		int row = 0, col = 0;
		
		for(int i = 1; i < N + 1; i++) {
			for(int j = 1; j < M + 1; j++) {
				if(map[i][j] == num) {			// 현재 번호에 해당하는 로봇의 위치를 저장
					row = i;
					col = j;
				}
			}
		}
				
		for(int move = 1; move < go + 1; move++) {		// 움직일 거리를 방향에 따라 구한 방향값을 이용해 다음 거리값에 담고
			int nextMoveR = rMove[num][0] * move;
			int nextMoveC = rMove[num][1] * move;
			
			// 맵을 넘어가는 경우
			if((row + nextMoveR) > N || (row + nextMoveR) < 1 || (col + nextMoveC) > M || (col + nextMoveC) < 1) {
				return ERROR_BLOCKED;
			}
			
			// 다른 로봇과 충돌하는 경우
			if(map[row + nextMoveR][col + nextMoveC] != 0) {
				oppo = map[row + nextMoveR][col + nextMoveC];	// 이땐 해당 로봇의 번호를 oppo 변수에 담아줌
				return ERROR_INTERACTED;
			}
		}
			
		// 정상적으로 완료한 경우, 로봇의 위치를 명령 수행 이후의 위치로 변경
		map[row][col] = 0;
		map[row + rMove[num][0] * go][col + rMove[num][1] * go] = num;
		
		return COMPLETE;
	}
	
	/**
	 * 
	 * 좌회전 메소드 (우회전과 동일)
	 */
	private static void turnL(int num, int repeat) {
		if(repeat >= 4) repeat %= 4;		// 반복횟수가 4이상인 경우 4로 나눈 나머지로 초기화
		
		while(repeat-- > 0) {							// 각 로봇이 보는 방향에서 repeat 횟수에 따라 반복적으로 방향을 바꿔줌			
			for(int i = 0; i < BEARING.length; i++) {
				if(BEARING[i].equals(campus[num])) {
					int nextIdx = (4 + i - 1) % 4;
					
					rMove[num][0] = DIRECTIONS[nextIdx][ROW];
					rMove[num][1] = DIRECTIONS[nextIdx][COL];
					campus[num] = BEARING[nextIdx];
					
					break;
				}
			}
		}
	}
	
	/**
	 * 
	 * 우회전 메소드
	 */
	private static void turnR(int num, int repeat) {
		if(repeat >= 4) repeat %= 4;
		
		while(repeat-- > 0) {							// 각 로봇이 보는 방향에서 repeat 횟수에 따라 반복적으로 방향을 바꿔줌			
			for(int i = 0; i < BEARING.length; i++) {
				if(BEARING[i].equals(campus[num])) {
					int nextIdx = (i + 1) % 4;

					rMove[num][0] = DIRECTIONS[nextIdx][ROW];
					rMove[num][1] = DIRECTIONS[nextIdx][COL];
					campus[num] = BEARING[nextIdx];
					
					break;
				}
			}
		}
	}
}
