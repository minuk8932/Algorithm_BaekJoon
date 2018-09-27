package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 2164번: 카드2
 *
 *	@see https://www.acmicpc.net/problem/2164/
 *
 */
public class Boj2164 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i < N + 1; i++) {
			q.offer(i);
		}
		
		while(q.size() != 1) {
			q.poll();			// 맨 위의 카드 버리고
			q.offer(q.poll());	// 그 다음 카드 아래로 깔아줌
		}
		
		System.out.println(q.poll());		// 마지막에 남는 카드 출력
	}
}
