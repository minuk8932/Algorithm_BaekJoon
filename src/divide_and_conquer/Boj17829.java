package divide_and_conquer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17829번: 222-풀링
 *
 *	@see https://www.acmicpc.net/problem/17829/
 *
 */
public class Boj17829 {
	private static int[][] map;
	private static final int[][] RANGES = {{0, 0}, {1, 0}, {0, 1}, {1, 1}};
	private static final int ROW = 1, COL = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(pulling(N));
	}
	
	private static int pulling(int n) {		
		while(n > 1) {
			ArrayList<Integer> save = new ArrayList<>();
			
			for(int i = 0; i < n; i += 2) {				
				for(int j = 0; j < n; j+= 2) {
					PriorityQueue<Integer> pq = new PriorityQueue<>();
					
					for(final int[] RANGE: RANGES) {
						int adjI = i + RANGE[ROW];
						int adjJ = j + RANGE[COL];
						
						pq.offer(-map[adjI][adjJ]);
					}
					
					pq.poll();								// remove max value
					save.add(-pq.poll());					// save next elements
				}
			}
			
			n /= 2;
			resize(n, save);								// resizing
		}
		
		return map[0][0];
	}
	
	private static void resize(int n, ArrayList<Integer> elements) {
		map = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				map[i][j] = elements.get(i * n + j);
			}
		}
	}
}
