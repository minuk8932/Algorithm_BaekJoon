package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17140번: 이차원 배열과 연산
 *
 *	@see https://www.acmicpc.net/problem/17140/
 *
 */
public class Boj17140 {
	private static int[][] matrix;
	private static PriorityQueue<Pair> pq = new PriorityQueue<>();
	
	private static class Pair implements Comparable<Pair>{
		int value;
		int cost;
		
		public Pair(int value, int cost) {
			this.value = value;
			this.cost = cost;
		}

		@Override
		public int compareTo(Pair p) {
			if(this.cost < p.cost) {
				return -1;
			}
			else if(this.cost > p.cost) {
				return 1;
			}
			else {
				if(this.value < p.value) return -1;
				else if(this.value > p.value) return 1;
				else return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());
		
		matrix = new int[100][100];
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(makeArray(r, c, k));
	}
	
	private static int makeArray(int R, int C, int K) {		
		int rSize = 3, cSize = 3;
		int result = 0;
		
		while(matrix[R][C] != K) {			
			int max = 0;
			
			if(rSize >= cSize) {				
				max = Math.max(remakeMatrix(rSize, cSize, true), max);
				cSize = max;
			}
			else {
				max = Math.max(remakeMatrix(cSize, rSize, false), max);
				rSize = max;
			}
			
			result++;
			if(result > 100) return -1;			// 100번 연산 후 값이 안나온 경우
		}
		
		return result;
	}
	
	private static int remakeMatrix(int r, int c, boolean flag) {
		int size = 0;
		
		for(int i = 0; i < r; i++) {
			int[] count = new int[101];
			
			for(int j = 0; j < c; j++) {
				count[flag ? matrix[i][j]: matrix[j][i]]++;			// 등장하는 숫자의 갯수
			}
			
			int resize = 0;
			for(int x = 1; x < 101; x++) {
				if(count[x] == 0) continue;
				pq.offer(new Pair(x, count[x]));
				resize += 2;
			}
			
			size = Math.max(resize, size);			// 최대 사이즈 -> 추후 부분 연산 후 r 크기가 됨
			
			for(int j = 0; j < resize; j += 2) {	// 배열 재구성
				Pair current = pq.poll();
				
				if(flag) {
					matrix[i][j] = current.value;
					matrix[i][j + 1] = current.cost;
				}
				else {
					matrix[j][i] = current.value;
					matrix[j + 1][i] = current.cost;
				}
			}
			
			for(int j = resize; j < 100; j++) {		// 범위 밖 잔여 숫자 초기화
				if(flag) matrix[i][j] = 0;
				else matrix[j][i] = 0;
			}
		}
		
		return size;
	}
}
