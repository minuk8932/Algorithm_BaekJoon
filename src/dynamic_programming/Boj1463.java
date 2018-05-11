package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author minchoba 
 * 백준 1463번: 1로 만들기
 *
 * @see https://www.acmicpc.net/problem/1463/
 *
 */
public class Boj1463 {
	private static final int MAX = 1_000_001;
	private static final int ZERO = 0;
	private static final int ONE = 1;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int val = Integer.parseInt(br.readLine());
		int[] token = new int[MAX];

		token[1] = ZERO;					// 1~3 각 연산에 따른 결과를 입력
		token[2] = ONE;
		token[3] = ONE;

		for (int i = 4; i <= val; i++) {		// 4부터 메모이제이션을 통한 값 도출

			int res = 0;

			if (i % 3 == ZERO) {			// 3으로 나누어 떨어지면, 해당 인덱스의 token을 받아오고
				res = token[i / 3];
			}

			if (i % 2 == ZERO) {		// 2로 나누어 떨어지면서
				if (res != ZERO) {					// 0이 아닌경우
					res = Math.min(token[i / 2], res);		// 결과에 2로나눈 인덱스의 토큰값과, 결과 중 최솟 값을 담아줌
				} else {				// 0인 경우, 2로 나누는 인덱스의 토큰값을 담음
					res = token[i / 2];
				}
			}

			if (res == ZERO) {			// 만약 0이면, 이전 인덱스 값을 결과에 담
				res = token[i - 1];
			} else {							// 0이아니면 최솟값을 받아옴
				res = Math.min(res, token[i - 1]);
			}
			token[i] = res + 1;
		}

		System.out.println(token[val]);		// 받아온 값에 해당하는 결과 출력
	}
}
