package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3190번: 뱀
 *
 *	@see https://www.acmicpc.net/problem/3190/
 *
 */
public class Boj3190 {
	private static Turn[] t = null;
	private static Point[] apples = null;
	private static int[] d = {0, 1};
	
	private static final char LEFT = 'L';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][N];
		
		apples = new Point[K];
		StringTokenizer st = null;
		for(int i = 0; i < apples.length; i++) {
			st = new StringTokenizer(br.readLine());
			apples[i] = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		}
		
		int L = Integer.parseInt(br.readLine());
		t = new Turn[L];
		for(int i = 0; i < t.length; i++) {
			st = new StringTokenizer(br.readLine());
			t[i] = new Turn(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}
		
		System.out.println(bfs(arr, N) + 1);		// 너비 우선 탐색을 통한 결과 출력
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
	 * 방향 회전 정보 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Turn{
		int time;
		char direction;
		
		public Turn(int time, char direction) {
			this.time = time;
			this.direction = direction;
		}
	}
	
	/**
	 * 너비 우선 탐색 메소드
	 * 
	 */
	private static int bfs(int[][] map, int n) {
		int timer = 0;
		
		Queue<Point> snake = new LinkedList<>();		// 뱀 큐
		Queue<Point> q = new LinkedList<>();			// 이동 큐
		
		snake.offer(new Point(0, 0));
		q.offer(new Point(0, 0));
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			hasTurn(timer);		// 방향 회전이 필요한지 메소드를 통해 검사
			
			int nextRow = current.row + d[0];		// 해당 방향에 맞춰 이동
			int nextCol = current.col + d[1];
			
			boolean needNot = isApple(new Point(nextRow, nextCol));		// 사과가 다음 칸에 존재했으면 꼬리를 자를 필요 없음, 반대의 경우 꼬리를 잘라야 함
			
			if(nextRow >= n || nextRow < 0 || nextCol >= n || nextCol < 0) return timer;		// 범위 밖으로 벗어난 경우 게임 종료
			
			int size = snake.size();			// 뱀의 크기만큼 반복문 실시
			while(size-- > 0) {
				Point tmp = snake.poll();
				
				if(tmp.row == nextRow && tmp.col == nextCol) return timer;		// 만약 자기 몸에 머리가 닿는 경우 게임 종료
				else snake.offer(new Point(tmp.row, tmp.col));			// 몸에 닿지 않을 경우 빼낸 정점을 다시 큐에 담아줌
			}
			
			timer++;										// 게임 시간 +1
			snake.offer(new Point(nextRow, nextCol));		// 다음 뱀의 머리를 둘 정점을 뱀큐에 저장
			
			if(!needNot) {			// 꼬리를 잘라야 했던 경우라면 가장 처음 들어온 정점을 빼줌
				snake.poll();
			}
			
			q.offer(new Point(nextRow, nextCol));		// 다음 이동할 정점을 큐에 저장
		}
		
		return timer;		// 위에서 끝나지 않을 경우
	}
	
	/**
	 * 방향 변경 메소드
	 * 
	 */
	private static void hasTurn(int time) {
		for(int i = 0; i < t.length; i++) {
			if(t[i].time == time) {
				if(t[i].direction == LEFT) {			// 해당 시간대에 방향을 왼쪽으로 변경을 해야하는 경우
					if(d[0] == 0 && d[1] == 1) {
						d[0] = -1;
						d[1] = 0;
					}
					else if(d[0] == 1 && d[1] == 0) {
						d[0] = 0;
						d[1] = 1;
					}
					else if(d[0] == 0 && d[1] == -1) {
						d[0] = 1;
						d[1] = 0;
					}
					else {
						d[0] = 0;
						d[1] = -1;
					}
				}
				else {									// 오른쪽
					if(d[0] == 0 && d[1] == 1) {
						d[0] = 1;
						d[1] = 0;
					}
					else if(d[0] == 1 && d[1] == 0) {
						d[0] = 0;
						d[1] = -1;
					}
					else if(d[0] == 0 && d[1] == -1) {
						d[0] = -1;
						d[1] = 0;
					}
					else {
						d[0] = 0;
						d[1] = 1;
					}
				}
			}
		}
	}
	
	/**
	 * 사과의 존재 유무 메소드
	 * 
	 */
	private static boolean isApple(Point next) {		
		for(int i = 0; i < apples.length; i++) {
			if(apples[i].row == next.row && apples[i].col == next.col) {		// 다음 이동할 정점에 사과가 존재할 경우
				apples[i] = new Point(-1, -1);			// 사과를 먹고
				return true;							// 참 반환
			}
		}
		
		return false;			// 사과가 없는 경우 거짓 반환
	}
}
