package heap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 14729번: 칠무해
 *
 *	@see https://www.acmicpc.net/problem/14729/
 *
 */
public class Boj14729 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Double> pq = new PriorityQueue<>();
		for(int i = 0; i < 7; i++) {
			pq.offer(-Double.parseDouble(br.readLine()));
		}
		
		for(int i = 7; i < N; i++) {
			double x = -Double.parseDouble(br.readLine());
			
			if(x > pq.peek()) {			// 고정 7개 입력 후, 가장 앞의 값(즉, 음수로 바꿨으므로 가장 큰 값과 비교)
				pq.poll();
				pq.offer(x);			// 가장 앞의 값보다 입력 값이 크면 맨 앞 제거 후 큐에 추가
			}
		}
		
		StringBuilder sb = new StringBuilder();
		double[] arr = new double[7];
		for(int i = 6; i >= 0; i--) {
			arr[i] = -pq.poll();
		}
		
		for(int i = 0; i < 7; i++) {
			sb.append(String.format("%.3f", arr[i])).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
}
