package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14754번: Pizza Boxes
 *
 *	@see https://www.acmicpc.net/problem/14754/
 *
 */
public class Boj14754 {
	
	private static class Pizza{
		int row, col;
		int value;
		boolean isMax;
		
		public Pizza(int value, boolean isMax) {
			this.value = value;
			this.isMax = isMax;
		}
		
		public Pizza(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Pizza[][] box = new Pizza[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int max = 0;
			Pizza mPoint = new Pizza(0, 0);
			
			for(int j = 0; j < M; j++) {
				box[i][j] = new Pizza(Integer.parseInt(st.nextToken()), false);
				
				if(box[i][j].value > max) {		// 행마다 가장 높은 피자 박스 높이 및 위치
					max = box[i][j].value;
					mPoint = new Pizza(i, j);
				}
			}
			
			box[mPoint.row][mPoint.col] = new Pizza(box[mPoint.row][mPoint.col].value, true);		// 갱신
		}
		
		System.out.println(sideRemove(N, M, box));
	}
	
	private static long sideRemove(int n, int m, Pizza[][] arr) {
		for(int i = 0; i < m; i++) {
			int max = 0;
			Pizza mPoint = new Pizza(0, 0);
			
			for(int j = 0; j < n; j++) {
				if(arr[j][i].value > max) {
					max = arr[j][i].value;
					mPoint = new Pizza(j, i);
				}
			}
			
			arr[mPoint.row][mPoint.col] = new Pizza(arr[mPoint.row][mPoint.col].value, true);
		}
		
		return getTotal(n, m, arr);
	}
	
	private static long getTotal(int n, int m, Pizza[][] arr) {
		long total = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j].isMax) continue;		// 최대 높이를 제외하고 모두 제거
				total += arr[i][j].value;
			}
		}
		
		return total;
	}
}
