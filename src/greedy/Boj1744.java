package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 
 * 	@author exponential-e
 *	백준 1744번: 수 묶기
 *
 *	@see https://www.acmicpc.net/problem/1744/
 *
 */
public class Boj1744 {
	private static boolean zero = false;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> nega = new PriorityQueue<>();
		PriorityQueue<Integer> posi = new PriorityQueue<>();
		
		int one = 0;
		for(int i = 0; i < N; i++) {
			int val = Integer.parseInt(br.readLine());
			if(val == 1) {						// 1의 값은 다 더해주는 것이 최대 
				one++;
				continue;
			}
			
			if(val < 0) nega.add(val);
			else if(val > 0) posi.add(-val);
			else zero = true;					// 0이 등장한 경우
		}
		
		System.out.println(getPacked(N, nega, 1) + getPacked(N, posi, -1) + one);
	}
	
	private static int getPacked(int n, PriorityQueue<Integer> pq, int flag) {		
		int result = 0;
		
		while(!pq.isEmpty()) {
			int val1 = pq.poll() * flag;
			
			if(pq.isEmpty()) {
				result += val1 < 0 && zero ? 0: val1;		// 마지막 값이 음수로 하나가 남을 경우 0이 등장한적이 있다면 0으로 바꿔줌
				break;
			}
			
			int val2 = pq.poll() * flag;
			result += val1 * val2;
		}
		
		return result;
	}
}
