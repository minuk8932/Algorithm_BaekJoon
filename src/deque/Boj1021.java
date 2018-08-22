package deque;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1021번: 회전하는 큐
 *
 *	@see https://www.acmicpc.net/problem/1021/
 *
 */
public class Boj1021 {
	private static LinkedList<Integer> dq = new LinkedList<>();
	
	private static int N = 0;
	private static int M = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {		// deque에 값을 차례로 담아줌
			dq.add(i + 1);
		}
		
		int[] elem = new int[M];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < M; i++) {					// 뽑을 값들을 배열에 저장
			elem[i] = Integer.parseInt(st.nextToken());
		}
		
		int res = 0;
		
		for(int i = 0; i < M; i++) {
			int half = getHalfSize();			// deque의 사이즈에 따른 중간 값을 메소드를 통해 갱신
			
			while(true) {
				if(queryOne() == elem[i]) {		// 가장 앞의 값이 현재 찾는 답과 같다면,
					dq.removeFirst();			// 가장 앞의 값을 빼고 반복문 종료
					break;
				}
				
				int pos = getIdx(elem[i]);		// 현재 찾는 값의 위치를 메소드를 통해 deque에서 찾음
				if(pos > half) {				// 찾는 값이 절반보다 뒤에 있는 경우
					queryThree();				// Query 3번 메소드 진행 후 결과 + 1
					res++;
				}
				else {							// 그 외
					queryTwo();					// Query 2번 메소드 진행 후 결과 + 1
					res++;
				}
			}
		}
		
		System.out.println(res);		// 결과 값 출력
	}
	
	/**
	 * 중간값 갱신 메소드
	 * 
	 */
	private static int getHalfSize() {
		int getHalf = dq.size() / 2;
		
		return getHalf;
	}
	
	/**
	 * 목적 값 위치 반환 메소드
	 * 
	 */
	private static int getIdx(int num) {		
		for(int i = 0; i < N; i++) {
			if(num == dq.get(i)) {
				return i;
			}
		}
		
		return 0;
	}
	
	/**
	 * deque의 가장 앞의 원소를 반환
	 * 
	 */
	private static int queryOne() {
		return dq.get(0);
	}
	
	/**
	 * deque의 값을 왼쪽으로 하나씩 밀어줌
	 * 
	 */
	private static void queryTwo() {
		int tmp = dq.removeFirst();
		dq.addLast(tmp);
	}
	
	/**
	 * deque의 값을 오른쪽으로 하나씩 밀어줌
	 * 
	 */
	private static void queryThree() {
		int tmp = dq.removeLast();
		dq.addFirst(tmp);
	}
}
