import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj13460 {
	private static final String SPACE = " ";
	
	private static final char BLOCK = '#';
	private static final char HOLE = 'O';
	private static final char RED = 'R';
	private static final char BLUE = 'B';
	
	private static final int FAIL = -1;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static char[][] map = null;
	private static int N = 0;
	private static int M = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		Point[] ballPos = new Point[2];
		Point dest = new Point(0, 0);
		
		for(int i = 0; i < N; i++){
			String line = br.readLine();
			
			for(int j = 0; j < M; j++){
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == HOLE){
					dest = new Point(i, j);
				}
				
				if(map[i][j] == RED){
					ballPos[0] = new Point(i, j);
				}
				
				if(map[i][j] == BLUE){
					ballPos[1] = new Point(i, j);
				}
			}
		}
		System.out.println(bfs(ballPos, dest));
	}
	
	private static class Point{
		int row, rowB;
		int col, colB;
				
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
		
		public Point(int row, int col, int rowB, int colB){
			this.row = row;
			this.col = col;
			this.rowB = rowB;
			this.colB = colB;
		}
	}
	
	private static int bfs(Point[] ball, Point dest){
		
		int[][] move = new int[N][M];
		int[][] moveB = new int[N][M];
		
		int[][][] isVisited = new int[N][M][2];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(ball[0].row, ball[0].col, ball[1].row, ball[1].col));
		
		isVisited[ball[0].row][ball[0].col][0] = 1;
		isVisited[ball[1].row][ball[1].col][1] = 1;
		
		while(!q.isEmpty()){
			Point current = q.poll();
			
			for(final int[] DIRECTION : DIRECTIONS){
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				int nextRowB = current.rowB + DIRECTION[ROW];
				int nextColB = current.colB + DIRECTION[COL];
				
				if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M){ 
					if(map[nextRow][nextCol] != BLOCK){		
						if(isVisited[nextRow][nextCol][0] != 1){
							isVisited[nextRow][nextCol][0] = 1;
							
							int moveTmp = move[current.row][current.col] + 1;
							int moveBtmp = move[current.rowB][current.colB] + 1;
							int destR = 0, destC = 0;
							int destRb = 0, destCb = 0;
							
							while(true){
								if(nextRow == dest.row && nextCol == dest.col){
									destR = nextRow;
									destC = nextCol;
								}
								
								if(map[nextRow][nextCol] == BLOCK || (nextRow == current.rowB && nextCol == current.colB)){
									nextRow -= DIRECTION[ROW];
									nextCol -= DIRECTION[COL];
									
									break;
								}
								
								nextRow += DIRECTION[ROW];
								nextCol += DIRECTION[COL];
								
								isVisited[nextRow][nextCol][0] = 1;
							}
							
							System.out.println(nextRow + " " + nextCol);
							
							while(true){
								if(nextRowB >= 0 && nextRowB < N && nextColB >= 0 && nextColB < M){	
									if(map[nextRowB][nextColB] != BLOCK){
										if(nextRowB == dest.row && nextColB == dest.col){
											destRb = nextRowB;
											destCb = nextColB;
										}
										
										nextRowB += DIRECTION[ROW];
										nextColB += DIRECTION[COL];
										
										if(nextRow == nextRowB && nextCol == nextColB){
											nextRowB -= DIRECTION[ROW];
											nextColB -= DIRECTION[COL];
											
											break;
										}
										
										isVisited[nextRowB][nextColB][1] = 1;
									}
									else{
										nextRowB -= DIRECTION[ROW];
										nextColB -= DIRECTION[COL];
										
										break;
									}
								}
							}
							
							q.offer(new Point(nextRow, nextCol, nextRowB, nextColB));
							
							System.out.println(nextRowB + " " + nextColB);
							
							move[nextRow][nextCol] = moveTmp;
							moveB[nextRowB][nextColB] = moveBtmp;
							
							
							if(destRb != 0 && destR !=0 && destC !=0 && destCb !=0){
								if((destRb == dest.row && destCb == dest.col) || (destRb == destR && destCb == destC)){
									return FAIL;
								}
							}
							
							if(destR == dest.row && destC == dest.col && move[destR][destC] <= 10){
								return move[nextRow][nextCol];
							}
						}
					}
				}
			}
		}
		
		return FAIL;
	}
}
