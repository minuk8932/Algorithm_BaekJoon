package sliding_window;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11003번: 최솟값 찾기
 *
 *	@see https://www.acmicpc.net/problem/11003
 *
 */
public class Boj11003 {
	private static final char SPACE = ' ';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		Deque<Integer> deq = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		int[] A = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			
			if(deq.isEmpty()) {			// 덱이 빈 경우 값을 담음
				deq.offerFirst(A[i]);
			}
			else {
				while(!deq.isEmpty() && deq.peekLast() > A[i]) { // 덱에 값이 존재하면서, 현재 들어온 값보다 마지막에 있는 값이 큰 경우 덱에서 제거
					deq.pollLast();
				}
				
				deq.offerLast(A[i]); // 이후 현재 들어온 값을 덱 끝에 담음
			}
			
			int idx = i - L;
			
			if(idx > 0) { 	// 윈도우의 움직임
				if(deq.peekFirst() == A[idx]) deq.pollFirst();	// 윈도우 크기에 따라 가장 앞의 값을 제거
			}
			
			sb.append(deq.peekFirst()).append(SPACE);		// 윈도우에 존재하는 값 중 최솟값을 버퍼에 저장
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}
