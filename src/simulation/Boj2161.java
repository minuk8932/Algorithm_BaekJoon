package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 2161번: 카드1
 *
 *	@see https://www.acmicpc.net/problem/2161/
 *
 */
public class Boj2161 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new LinkedList<>();
		for(int i = 0; i < N; i++) {			// 카드 쌓기
			q.offer(i + 1);
		}
		
		StringBuilder sb = new StringBuilder();
		while(q.size() > 1) {
			sb.append(q.poll()).append(SPACE);		// 가장 위의 카드는 빼면서 버퍼에 저장
			q.offer(q.poll());						// 바로 다음 카드는 빼서 다시 밑으로 깔아줌
		}
		
		sb.append(q.poll());					// 마지막에 1장 남은 카드를 버퍼에 담고
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}
