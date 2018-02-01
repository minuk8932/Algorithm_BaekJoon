package depth_first_search;
/*
 * DFS(Depth First Search) 그래프 탐색		시간 복잡도 : n (갈 수 있는 노드의 갯수)
 * 최단 거리를 찾아 보세요 -> stack 이용
 * 	 0 1 2 3 4													->
 * 0 0 1 0 0 0																|(2,5)|			
 * 1 1 1 0 1 0																|	  | => (1,4) / pop()
 * 2 0 1 1 1 1							->									|(2,4)|
 * 3 0 1 0 0 0				|(2,1)|		|(2,1)|							|(2,3)|
 * 								|(1,1)|			|	  | => 갈 곳이 없더라... pop() 	|(2,1)|
 * 								|(1,2)|		|(1,2)|							|(1,2)|
 * 								|(0,2)|		|(0,2)|							|(0,2)|
 * 																					최종 경로
 */

public class AdjacentMatrixDFS {
	public static final String SPACE = " ";

	private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;			// 		동		남			북			서	
	private static final int COL = 1;

	private static int[][] map = {
		{0, 1, 0 ,0 ,0},
		{1, 1, 0, 1, 0},
		{0, 1, 1, 1, 1},
		{0, 1, 0, 0, 0}
		};
	
	private static Point des = new Point(2, 4);

	public static void main(String[] args) throws Exception {
		dfs(new Point(0, 1));
			// 출발 위치 값 지정 후  dfs 함수 실행
		
		System.out.println("I cann't find the route... rollback");
			// 만약 dfs를 다 돌았지만 길을 못 찾았을 경우
	}

	private static void dfs(Point p) {
		System.out.println(p.row + " " + p.col);
			// 함수 실행 시 이동하는 경로 표시
		
		if(p.row == des.row && p.col == des.col){
			System.out.println("finish");
			System.exit(0);				// prog terminate
		}
			// 목적지에 도착 시 종료
		
		for (final int[] DIRECTION : DIRECTIONS) {
			int nextROW = p.row + DIRECTION[ROW];
			int nextCOL = p.col + DIRECTION[COL];

			if (0 <= nextROW && nextROW < 4 && 0 <= nextCOL && nextCOL < 5) {	// 해당 맵을 벗어나지 못하게
				if (map[nextROW][nextCOL] == 1) {
					map[p.row][p.col] = 0;				// 이미 왔던 길, 뒤로 못가게 막는다 : 무한루프 방지
					dfs(new Point(nextROW, nextCOL));	// 재귀 호출을 통해 계속 진행 시키고
					map[p.row][p.col] = 1;				// 재귀가 막혔네, 앞 쪽을 막음 뒤로 후진
				}
			}
		}
	}
	
	
		// 정점을 가져올 class 선언
	private static class Point {
		public int row;
		public int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
