package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9328번: 열쇠
 *
 *	@see https://www.acmicpc.net/problem/9328/
 *
 */
public class Boj9328 {
	private static boolean[][] isVisited = null;
	private static char map[][] = null;
	private static int documents = 0;
	private static int hasKey = 0;

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	private static final char BLOCK = '*';
	private static final char WAY = '.';
	private static final char DOC = '$';
	private static final char NO_KEY = '0';
	private static final char KEY = 'a';
	private static final char DOOR_LOCK = 'A';
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			map = new char[h + 2][w + 2];
			isVisited = new boolean[h + 2][w + 2];

			for (int i = 1; i < h + 1; i++) {
				String line = br.readLine();

				for (int j = 1; j < w + 1; j++) {
					map[i][j] = line.charAt(j - 1);
				}
			}
			
			hasKey = 1 << 26;		// key 비트 초기화

			for (char key : br.readLine().toCharArray()) {		// key를 가진게 없으면 반복문 종료, 있으면 추가
				if (key == NO_KEY) break;
				hasKey = hasKey | 1 << key - KEY;
			}
			
			documents = 0;
			bfs(h + 2, w + 2);		// 너비 우선 탐색을 통한 획득 가능한 문서 갯수 확인
			
			sb.append(documents).append(NEW_LINE);	// 획득한 문서를 버퍼에 담고
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 *  정점 이너 클래스
	 * 	@author minchoba
	 *
	 */
	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	/**
	 *  너비 우선 탐색 메소드
	 *
	 */
	private static void bfs(int N, int M) {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if(row > 0 && row < N - 1 && col > 0 && col < M - 1) continue;		// 가장자리 시작이 아닌 경우
				if(isVisited[row][col]) continue;								// 이미 방문한 지점인 경우
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row, col));

				isVisited[row][col] = true;

				while (!q.isEmpty()) {
					Point current = q.poll();

					for (final int[] DIRECTION : DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];

						if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
							// 맵에 key가 등장한 경우
							if (map[nextRow][nextCol] >= KEY && map[nextRow][nextCol] <= KEY + 26) {
								hasKey = hasKey | 1 << map[nextRow][nextCol] - KEY;			// key를 추가하고
								map[nextRow][nextCol] = WAY;							// 해당 지점을 WAY로 변경 후
								isVisited = new boolean[N][M];							// 방문 배열 초기화								
								isVisited[nextRow][nextCol] = true;

								q.offer(new Point(nextRow, nextCol));
							}
							// 맵에 문이 등장한 경우
							else if (map[nextRow][nextCol] >= DOOR_LOCK && map[nextRow][nextCol] <= DOOR_LOCK + 26) {
								if(!isVisited[nextRow][nextCol]) {
									int alphaCost = map[nextRow][nextCol] - DOOR_LOCK;
									int comp = (int) Math.pow(2, alphaCost);
									
									if((hasKey & 1 << alphaCost) == comp) {		// 키를 가진 문인지 확인해서 열 수 있는 경우
										map[nextRow][nextCol] = WAY;			// 해당 문을 WAY로 바꾸고
										isVisited[nextRow][nextCol] = true;		// 방문함으로 처리
										
										q.offer(new Point(nextRow, nextCol));
									}
								}	
							}

							else if (map[nextRow][nextCol] != BLOCK) {			// WAY 또는 DOC의 경우
								if(!isVisited[nextRow][nextCol]) {
									if(map[nextRow][nextCol] == DOC) {		// 해당 지점에 문서가있으면
										documents++;						// 문서 개수 + 1
										map[nextRow][nextCol] = WAY;		// 해당 지점을 WAY 변경 후
										
										isVisited = new boolean[N][M];		// 방문 배열 초기화
									}
									isVisited[nextRow][nextCol] = true;
									
									q.offer(new Point(nextRow, nextCol));
								}
							}
						}
					}
				}
			}
		}
	}
}
