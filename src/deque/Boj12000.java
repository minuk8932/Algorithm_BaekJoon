package deque;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 12000번: Circular Barn
 *
 *	@see https://www.acmicpc.net/problem/12000
 *
 */
public class Boj12000 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] rooms = new int[n];
		int total = 0;
		
		for(int i = 0; i < n; i++) {
			rooms[i] = Integer.parseInt(br.readLine());
			total += rooms[i];
		}
		
		System.out.println(minCost(n, total, rooms));
	}
	
	private static int minCost(int n, int sum, int[] arr) {
		int min = Integer.MAX_VALUE;
		
		for(int in = 0; in < n; in++) {
			int spare = sum - arr[in];
			int sub = spare;
			int idx = (in + 1) % n;
			
			do {						// 각 경우마다 소들의 이동거리를 구한 후
				spare -= arr[idx];
				sub += spare;
				
				idx = (idx + 1) % n;
			} while(in != idx);

			if(sub < min) min = sub;	// 최솟값 저장
		}
		
		return min;
	}
}
