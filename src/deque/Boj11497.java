package deque;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11497번: 통나무 건너뛰기
 *
 *	@see https://www.acmicpc.net/problem/11497/
 *
 */
public class Boj11497 {
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			
			int[] tong = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				tong[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(tong);
			
			Deque<Integer> deq = new LinkedList<>();
			
			for(int i = N - 1; i >= 0; i--) {			// 가장 큰 수부터 차례로 좌우로 하나씩 숫자를 담아줌
				if(i % 2 == 0) deq.offerFirst(tong[i]);
				else deq.offerLast(tong[i]);
			}
			
			int max = Math.abs(deq.peekFirst() - deq.peekLast());		// 맨 앞과 맨뒤의 인접한 통나무의 차이를 구하고
			int num1 = deq.poll(), num2 = 0;
			
			while(!deq.isEmpty()) {
				num2 = deq.poll();
				
				int tmp = Math.abs(num1 - num2);		// 각 통나무 별 차이를 구해서 가장 큰 수를 max에 저장
				max = tmp > max ? tmp : max;
				
				num1 = num2;
			}
			
			sb.append(max).append(NEW_LINE);			// 케이스 별 결과를 버퍼에 담고
		}
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
}
