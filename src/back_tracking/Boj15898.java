package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 15898번: 피아의 아틀리에~신비한 대회의 연금술사~
 *
 *	@see https://www.acmicpc.net/problem/15898/
 *
 */
public class Boj15898 {
	private static int[] arr = new int[3];
	private static boolean[] visit;
	private static Gama[][][][][] total;
	private static Gama[][][] gam;
	private static int[] check;
	
	private static int result;
	private static int n;
	
	private static final char RED = 'R', GREEN = 'G', BLUE = 'B', WHITE = 'W';
	private static final Point[] IDX = {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)};
	
	private static class Gama{
		int val;
		char alpha;
		
		public Gama(int val, char alpha) {
			this.val = val;
			this.alpha = alpha;
		}
	}
	
	private static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		gam = new Gama[n][4][4];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = null;
			
			for(int x = 0; x < 4; x++) {
				st = new StringTokenizer(br.readLine());
				for(int y = 0; y < 4; y++) {
					gam[i][x][y] = new Gama(Integer.parseInt(st.nextToken()), WHITE);
				}
			}
			
			for(int x = 0; x < 4; x++) {
				st = new StringTokenizer(br.readLine());
				for(int y = 0; y < 4; y++) {
					gam[i][x][y].alpha = st.nextToken().charAt(0);
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			visit = new boolean[10];
			backTracking(i, 0);
		}
		
		System.out.println(result);
	}
	
	private static void backTracking(int current, int count) {
		if(visit[current]) return;
		visit[current] = true;
		
		arr[count] = current;							// select three material
		
		if(count == 2) {
			total = new Gama[3][4][4][5][5];			// [idx][turn][start][row][col]
			reset();
			makeSum();
			return;
		}
		
		for(int next = 0; next < n; next++) {
			if(visit[next]) continue;
			
			backTracking(next, count + 1);
			visit[next] = false;
		}
	}
	
	private static void makeSum() {
		for(int seq = 0; seq < arr.length; seq++) {
			for(int w = 0; w < 4; w++) {
				for(int x = 0; x < 4; x++) {
					calculate(seq, w, x);
				}
			}
		}
		
		int sum = getSum();
		if(sum > result) result = sum;
	}
	
	private static void calculate(int i, int j, int k) {
		Point p = IDX[j];
		
		int a = k < 2 ? 0: 3;					// gam's idx
		int b = k % 3 == 0 ? 0: 3;
		int adder = a == 0 ? 1: -1;
		int bdder = b == 0 ? 1: -1;
		
		for(int y = p.x; y < p.x + 4; y++) {
			for(int z = p.y; z < p.y + 4; z++){
				total[i][j][k][y][z] = new Gama(gam[arr[i]][a][b].val, gam[arr[i]][a][b].alpha);		// save each case
				
				if(k % 2 != 0) a += adder;
				else b += bdder;
			}
			
			if(k % 2 == 0) {				// turning
				a += adder;
				b = k % 3 == 0 ? 0: 3;
			}
			else {
				b += bdder;
				a = k < 2 ? 0: 3;
			}
		}
	}
	
	private static int getSum() {
		int res = 0;
		
		for(int a = 0; a < 4; a++) {						// make sum with total case
			for(int b = 0; b < 4; b++) {
				for(int c = 0; c < 4; c++) {
					for(int d = 0; d < 4; d++) {
						for(int e = 0; e < 4; e++) {
							for(int f = 0; f < 4; f++) {
								check = new int[4];
								
								for(int x = 0; x < 5; x++) {
									for(int y = 0; y < 5; y++) {
										int val = total[0][a][b][x][y].val;
										char color = total[0][a][b][x][y].alpha;
								
										if(val < 0) val = 0;
										else if(val > 9) val = 9;
										
										 val += total[1][c][d][x][y].val;
										
										if(total[1][c][d][x][y].alpha != WHITE) color = total[1][c][d][x][y].alpha;
								
										if(val < 0) val = 0;
										else if(val > 9) val = 9;
								
										val += total[2][e][f][x][y].val;
										
										if(val < 0) val = 0;
										else if(val > 9) val = 9;
										
										if(total[2][e][f][x][y].alpha != WHITE) color = total[2][e][f][x][y].alpha;
										
										if(color == WHITE) continue;
										colorCheck(color, val);
									}
								}
								
								int sum = check[0] * 7 + check[1] * 5 + check[2] * 3 + check[3] * 2;								
								if(sum > res) res = sum;
							}
						}
					}
				}
			}
		}

		return res;
	}
	
	private static void colorCheck(char color, int val) {		
		if(color == RED) check[0] += val;					// count color
		else if(color == BLUE) check[1] += val;
		else if(color == GREEN) check[2] += val;
		else check[3] += val;
	}
	
	private static void reset() {
		for(int i = 0; i < 3; i++) {
			for(int w = 0; w < 4; w++) {
				for(int x = 0; x < 4; x++) {
					for(int y = 0; y < 5; y++) {
						for(int z = 0; z < 5; z++){
							total[i][w][x][y][z] = new Gama(0, WHITE);
						}
					}
				}
			}
		}
	}
}
