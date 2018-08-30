package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12789번: 도키도키 간식드리미
 *
 *	@see https://www.acmicpc.net/problem/12789/
 *
 */
public class Boj12789 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Stack<Integer> lifo = new Stack<>();
		int[] pass = new int[N + 1];
		int idx = 1;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(idx == num) {		// 순번이 맞다면 간식 배부
				pass[idx++] = num;
			}
			else {						// 순번이 맞지 않은 경우
				while(!lifo.isEmpty()) {
					if(lifo.peek() == idx) pass[idx++] = lifo.pop();	// 대기열 가장 앞의 값이 옳은 순번이면 간식배부
					else break;		// 옳지 않으면 대기열 탐색종료
				}
				
				lifo.push(num);		// 대기열 탐색 종료 후 현 차례 인원을 대기열로 보냄
			}
		}
		
		while(!lifo.isEmpty()) pass[idx++] = lifo.pop();	// 대기열에 남은 인원을 모두 차례로 간식 배부
		
		boolean notOrdered = false;
		
		for(int i = 1; i < N + 1; i++) {
			if(i != pass[i]) {			// 간식 배부 순서를 최종적으로 비교했을때 하나라도 다르다면
				notOrdered = true;		// 순서가 옳바르지않다 판단 후 반복문 종료
				break;
			}
		}
		
		System.out.println(notOrdered ? "Sad" : "Nice");	// 순서가 옳바르지 않다면 Sad, 아니면 Nice 출력
	}
}
