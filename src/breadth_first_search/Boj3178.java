package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3178번 : 양치기 꿍
 *
 *	@see https://www.acmicpc.net/problem/3178/
 *
 */
public class Boj3178 {
	private static final String SPACE = " ";
	
	private static final char FENCE = '#';
	private static final char SHEEP = 'k';
	private static final char WOLF = 'v';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] farm = new char[R][C];
		for(int i = 0; i < R; i++){
			farm[i] = br.readLine().toCharArray();
		}
		
		boolean[][] isVisited = new boolean[R][C];
		int surviveO = 0, surviveV = 0;
		
		for(int row = 0; row < R; row++){									// 농장안에서 펜스를 제외하고, 방문하지 않은 부분 탐색을 시작
			for(int col = 0; col < C; col++){
				if(farm[row][col] != FENCE && !isVisited[row][col]){
					isVisited[row][col] = true;
					
					int cntO = 0, cntV = 0;
					
					if(farm[row][col] == WOLF){								// 시작하는 시점에서 늑대가 존재 할 경우
						cntV++;
					}
					
					if(farm[row][col] == SHEEP){								// 시작하는 시점에서 양이 존재 할 경우
						cntO++;
					}
					
					Queue<Point> q = new LinkedList<>();
					q.offer(new Point(row, col));
					
					while(!q.isEmpty()){											// 시작 지점에서 펜스 내부 주변지역 탐색 시작
						Point current = q.poll();
						
						for(final int[] DIRECTION : DIRECTIONS){
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C){				// 주변지역 탐색 : 방문하지 않았으면서 펜스가 아닌지역
								if(!isVisited[nextRow][nextCol] && farm[nextRow][nextCol] != FENCE){
									isVisited[nextRow][nextCol] = true;
									
									if(farm[nextRow][nextCol] == SHEEP){						// 주변지역에 양이 존재 할 경우
										cntO++;
									}
									
									if(farm[nextRow][nextCol] == WOLF){						// 주변지역에 늑대가 존재 할 경우
										cntV++;
									}
									
									q.offer(new Point(nextRow, nextCol));					// 다음 주변지역 탐색
								}
							}
						}
					}
					if(cntO > cntV){																		// 한 펜스 내의 탐색이 끝났을때, 양의 수가 늑대 수보다 많은 경우에만 살아남은 수에 추가
						surviveO += cntO;
					}
					
					if(cntO <= cntV){																	// 한 펜스 내의 탐색이 끝났을때, 늑대의 수가 양의 수보다 크거나 같은 경우에만 살아남은 수에 추가
						surviveV += cntV;
					}
				}
			}
		}
		
		System.out.println(surviveO + " " + surviveV);										// 결과값 한번에 출력
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *	정점 이너 클래스
	 */
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
}
