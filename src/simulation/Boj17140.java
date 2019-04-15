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
		
		int[][] matrix = new int[100][100];
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(makeArray(r, c, k, matrix));
	}
	
	private static int makeArray(int R, int C, int K, int[][] arr) {
		int result = 0;
		PriorityQueue<Pair> pq;
		
		int rSize = 3, cSize = 3;
		
		while(arr[R][C] != K) {			
			int max = 0;
			
			if(rSize >= cSize) {				
				for(int i = 0; i < rSize; i++) {
					pq = new PriorityQueue<>();
					
					int[] count = new int[101];
					for(int j = 0; j < cSize; j++) {
						count[arr[i][j]]++;			// 등장하는 숫자의 갯수
					}
					
					int resize = 0;
					for(int x = 1; x < 101; x++) {
						if(count[x] == 0) continue;
						pq.offer(new Pair(x, count[x]));
						resize++;
					}
					
					resize *= 2;							// 갱신 사이즈
					max = Math.max(resize, max);			// 최대 사이즈 -> 추후 부분 연산 후 col 크기가 됨
					
					for(int j = 0; j < resize; j += 2) {	// 배열 재구성
						Pair current = pq.poll();
						arr[i][j] = current.value;
						arr[i][j + 1] = current.cost;
					}
					
					for(int j = resize; j < 100; j++) {		// 범위 밖 잔여 숫자 초기화
						arr[i][j] = 0;
					}
				}
				
				cSize = max;								// col 사이즈 갱신
			}
			else {
				for(int i = 0; i < cSize; i++) {			// row 연산과 같음
					pq = new PriorityQueue<>();
					
					int[] count = new int[101];
					for(int j = 0; j < rSize; j++) {
						count[arr[j][i]]++;
					}
					
					int resize = 0;
					for(int x = 1; x < 101; x++) {
						if(count[x] == 0) continue;
						pq.offer(new Pair(x, count[x]));
						resize++;
					}
					
					resize *= 2;
					max = Math.max(resize, max);
					
					for(int j = 0; j < resize; j += 2) {
						Pair current = pq.poll();
						arr[j][i] = current.value;
						arr[j + 1][i] = current.cost;
					}
					
					for(int j = resize; j < 100; j++) {
						arr[j][i] = 0;
					}
				}
				
				rSize = max;
			}
			
			result++;
			if(result > 100) return -1;			// 100번 연산 후 값이 안나온 경우
		}
		
		
		return result;
	}
}
