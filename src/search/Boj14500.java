package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14500번: 테트로미노
 *
 *	@see https://www.acmicpc.net/problem/14500/
 *
 */
public class Boj14500 {
	private static int N, M;
	private static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(maxValue());
	}
	
	private static int maxValue() {
		int count = 0;
		
		for(int tetro = 0; tetro < 5; tetro++) {
			int cost = getSize(tetro);
			if(cost > count) count = cost;
		}
		
		return count;
	}
	
	private static int getSize(int shape) {
		int value = 0;
		
		switch (shape) {
		case 0:					// ㅡ
			value = line();
			break;
			
		case 1:					// ㅁ
			value = square();
			break;
			
		case 2:					// ㄴ
			value = curve();
			break;
			
		case 3:					// ㄹ
			value = zigzag();
			break;
			
		case 4:					// ㅗ
			value = orthogonal();
			break;
		}
		
		return value;
	}
	
	private static int line() {
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int size = 0;
				int loop = j;
				
				while(loop - j < 4) {
					if(loop >= M) break;
					size += map[i][loop++];
				}
				
				if(loop - j == 4) max = Math.max(max, size);
				
				size = 0;
				loop = i;
				
				while(loop - i < 4) {
					if(loop >= N) break;
					size += map[loop++][j];
				}
				
				if(loop - i == 4) max = Math.max(max, size);
			}
		}
		
		return max;
	}
	
	private static int square() {
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int[] nextRow = {i + 1, i + 1, i};
				int[] nextCol = {j + 1, j, j + 1};
				
				if(i + 1 >= N || j + 1 >= M) continue;
				int sum = map[i][j];
				
				for(int index = 0; index < 3; index++) {
					sum += map[nextRow[index]][nextCol[index]];
				}
				
				max = Math.max(max, sum);
			}
		}
		
		return max;
	}
	
	private static int curve() {
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int[][] nextRow = {
						{i , i + 1, i + 1, i + 1},
						{i, i, i, i - 1},
						{i + 1, i, i, i},
						{i, i, i, i + 1},
						{i, i + 1, i + 2, i + 2},
						{i, i, i + 1, i + 2},
						{i, i + 1, i + 2, i + 2},
						{i, i, i + 1, i + 2}};
				
				int[][] nextCol = {
						{j, j, j + 1, j + 2},
						{j, j + 1, j + 2, j + 2},
						{j, j, j + 1, j + 2},
						{j, j + 1, j + 2, j + 2},
						{j, j, j, j + 1},
						{j + 1, j, j, j},
						{j, j, j, j - 1},
						{j, j + 1, j + 1, j + 1}};
				
				for(int x = 0; x < 8; x++) {
					int sum = 0, count = 0;
					
					for(int y = 0; y < 4; y++) {
						if(nextRow[x][y] < 0 || nextRow[x][y] >= N || nextCol[x][y] < 0 || nextCol[x][y] >= M) break;
						
						sum += map[nextRow[x][y]][nextCol[x][y]];
						count++;
					}
					
					if(count == 4) max = Math.max(sum, max);
				}
			}
		}
		
		return max;
	}
	
	private static int zigzag() {
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int[][] nextRow = {
						{i, i, i + 1, i + 1},
						{i, i, i - 1, i - 1},
						{i, i + 1, i + 1, i + 2},
						{i, i + 1, i + 1, i + 2}};
				
				int[][] nextCol = {
						{j, j + 1, j + 1, j + 2},
						{j, j + 1, j + 1, j + 2},
						{j, j, j + 1, j + 1},
						{j, j, j - 1, j - 1}};
				
				for(int x = 0; x < 4; x++) {
					int sum = 0, count = 0;
					
					for(int y = 0; y < 4; y++) {
						if(nextRow[x][y] < 0 || nextRow[x][y] >= N || nextCol[x][y] < 0 || nextCol[x][y] >= M) break;
						
						sum += map[nextRow[x][y]][nextCol[x][y]];
						count++;
					}
					
					if(count == 4) max = Math.max(sum, max);
				}
			}
		}
		
		return max;
	}
	
	private static int orthogonal() {
int max = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int[][] nextRow = {
						{i, i, i, i + 1},
						{i, i, i, i - 1},
						{i, i + 1, i + 2, i + 1},
						{i, i + 1, i + 2, i + 1}};
				
				int[][] nextCol = {
						{j, j + 1, j + 2, j + 1},
						{j, j + 1, j + 2, j + 1},
						{j, j, j, j + 1},
						{j, j, j, j - 1}};
				
				for(int x = 0; x < 4; x++) {
					int sum = 0, count = 0;
					
					for(int y = 0; y < 4; y++) {
						if(nextRow[x][y] < 0 || nextRow[x][y] >= N || nextCol[x][y] < 0 || nextCol[x][y] >= M) break;
						
						sum += map[nextRow[x][y]][nextCol[x][y]];
						count++;
					}
					
					if(count == 4) max = Math.max(sum, max);
				}
			}
		}
		
		return max;
	}
}
