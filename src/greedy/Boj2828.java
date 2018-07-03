package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 * 백준 2828번: 사과 담기 게임
 *
 * @see https://www.acmicpc.net/problem/2828/
 *
 */
public class Boj2828 {
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 계산
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int J = Integer.parseInt(br.readLine());

		int start = 1; 	// 바구니의 시작
		int end = M; 	// 바구니의 끝

		int diff = M; 	// 움직일 거리를 체크할 변수
		int move = 0;	// 바구니가 움직인 횟수

		while (J-- > 0) {
			int apple = Integer.parseInt(br.readLine()); 	// 사과가 떨어지는 위치

			if (apple < start) {		// 사과가 바구니 앞쪽보다 앞에 떨어지는 경우
				diff = start;			// 가장 앞쪽 값을 diff에 저장
				start = apple;			// 바구니가 사과까지 움직임
				end = apple + M - 1;	// 바구니의 끝 값을 크기에 맞춰 계산하여 갱신
			}

			else if (apple > end) {		// 사과가 바구니 뒷쪽보다 뒤에 떨어지는 경우, 앞쪽과 반대로 작동
				diff = end;
				end = apple;
				start = apple - M + 1;
			}

			else {						// 사과가 바구니 내에 다시 떨어지는 경우
				diff = apple;
			}

			move += Math.abs(apple - diff);		// 사과와 최소 떨어진 거리를 양수로 바꾸어 move에 합해줌
		}

		System.out.println(move);		// 결과 값 출력
	}
}
