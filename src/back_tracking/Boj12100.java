package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12100번: 2048 (Easy)
 *
 *	@see https://www.acmicpc.net/problem/12100/
 *
 */
public class Boj12100 {
	private static int[][] seq = new int[1025][5];
	private static int[][] fix;
	
	private static boolean[] isVisited = new boolean[33334];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		fix = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				fix[i][j] = map[i][j];
			}
		}
		
		for(int i = 0; i < 4; i++) {			// 방향 순열 생성
			backTracking(i, 0, 0);
		}
		
		System.out.println(play(N, map));
	}
	
	private static void backTracking(int current, int count, int depth) {		
		if(count == 5) {
			int index = 0;
			
			for(int i = 4; i >= 0; i--) {					// 탐색된 순열을 인덱스화
				index += seq[depth][4 - i] * Math.pow(10, i);
			}
			
			if(isVisited[index]) return;
			isVisited[index] = true;
			
			depth++;
			return;
		}
		
		seq[depth][count] = current;
		
		for(int next = 0; next < 4; next++) {
			backTracking(next, count + 1, depth);
		}
	}
	
	private static int play(int n, int[][] arr) {
		String[] move = getQuery();				// 만들어 낸 방향 순열로 쿼리 생성
		int max = 0;
		
		for(int i = 0; i < move.length; i++) {			
			for(int q = 0; q < move[i].length(); q++) {
				int way = move[i].charAt(q) - '0';
				
				switch (way) {						// 쿼리 내용에 따라 모든 방향에 대한 경우의 수를 돌려줌
				case 0:
					for(int col = 0; col < n; col++) {
						for(int row = n - 2; row >= 0; row--) {
							int push = 0;
							
							while(row - push > 0 && arr[row - push][col] == 0) {
								push++;
							}
							
							if(arr[row + 1][col] == arr[row - push][col]) {
								arr[row + 1][col] += arr[row - push][col];
								arr[row - push][col] = 0;
							}
						}
					}
					
					for(int col = 0; col < n; col++) {
						for(int row = n - 2; row >= 0; row--) {
							if(arr[row + 1][col] == 0) {
								int diff = 1;
								
								while((diff + row) < n && arr[row + diff][col] == 0) {
									diff++;
								}
								
								arr[row + diff - 1][col] = arr[row][col];
								arr[row][col] = 0;
							}
						}
					}
					
					break;
					
				case 1:
					for(int row = 0; row < n; row++) {
						for(int col = n - 2; col >= 0; col--) {
							int push = 0;
							
							while(col - push > 0 && arr[row][col - push] == 0) {
								push++;
							}
							
							if(arr[row][col + 1] == arr[row][col - push]) {
								arr[row][col + 1] += arr[row][col - push];
								arr[row][col - push] = 0;
							}
						}
					}
					
					for(int row = 0; row < n; row++) {
						for(int col = n - 2; col >= 0; col--) {
							if(arr[row][col + 1] == 0) {
								int diff = 1;
								
								while((diff + col) < n && arr[row][col + diff] == 0) {
									diff++;
								}
								
								arr[row][col + diff - 1] = arr[row][col];
								arr[row][col] = 0;
							}
						}
					}
					
					break;
					
				case 2:
					for(int col = 0; col < n; col++) {
						for(int row = 1; row < n; row++) {	
							int push = 0;
							
							while(row + push < n - 1 && arr[row + push][col] == 0) {
								push++;
							}
							
							if(arr[row - 1][col] == arr[row + push][col]) {
								arr[row - 1][col] += arr[row + push][col];
								arr[row + push][col] = 0;
							}
						}
					}
					
					for(int col = 0; col < n; col++) {
						for(int row = 1; row < n; row++) {
							if(arr[row - 1][col] == 0) {
								int diff = 1;
								
								while((row - diff) >= 0 && arr[row - diff][col] == 0) {
									diff++;
								}
								
								arr[row - diff + 1][col] = arr[row][col];
								arr[row][col] = 0;
							}
						}
					}
					
					break;
					
				case 3:
					for(int row = 0; row < n; row++) {
						for(int col = 1; col < n; col++) {
							int push = 0;
							
							while(col + push < n - 1 && arr[row][col + push] == 0) {
								push++;
							}
							
							if(arr[row][col - 1] == arr[row][col + push]) {
								arr[row][col - 1] += arr[row][col + push];
								arr[row][col + push] = 0;
							}
						}
					}
					
					for(int row = 0; row < n; row++) {
						for(int col = 1; col < n; col++) {
							if(arr[row][col - 1] == 0) {
								int diff = 1;
								
								while((col - diff) >= 0 && arr[row][col - diff] == 0) {
									diff++;
								}
								
								arr[row][col - diff + 1] = arr[row][col];
								arr[row][col] = 0;
							}
						}
					}
					
					break;
				}
			}
			
			max = Math.max(getMax(n, arr), max);
			arr = init(n);			// 맵 초기화
		}
		
		return max;
	}
	
	private static int[][] init(int n) {
		int[][] arr = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				arr[i][j] = fix[i][j];
			}
		}
		
		return arr;
	}
	
	private static String[] getQuery() {
		String[] str = new String[1024];
		int index = 0;
		
		for(int i = 0; i < isVisited.length; i++) {
			if(!isVisited[i]) continue;
			
			str[index] = makeString(i);		// 자릿수가 짧은 것은 앞에 0을 붙여 쿼리문 생성
			index++;
		}
		
		return str;
	}
	
	private static String makeString(int num) {
		StringBuilder sb = new StringBuilder();
		int loop =  5 - String.valueOf(num).length();
		
		while(loop-- > 0) {
			sb.append(0);
		}
		
		return sb.toString() + num;
	}
	
	private static int getMax(int n, int[][] arr) {
		int value = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(value < arr[i][j]) value = arr[i][j];
			}
		}
		
		return value;
	}
}
