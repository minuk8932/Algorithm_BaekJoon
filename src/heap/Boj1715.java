package heap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 1715번: 카드 정렬하기
 *
 *	@see https://www.acmicpc.net/problem/1715/
 *
 */
public class Boj1715 {
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> prior = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			prior.offer(Integer.parseInt(br.readLine()));
		}
		
		System.out.println(compareCounting(prior));			// 결과 출력
	}
	
	private static int compareCounting(PriorityQueue<Integer> pq) {
		int total = 0;
		
		while(pq.size() > 1){					// 묶음 0인 경우 비교 x
			int tmp = pq.poll() + pq.poll();
			
			pq.offer(tmp);
			total += tmp;
		}
		
		return total;
	}
}
