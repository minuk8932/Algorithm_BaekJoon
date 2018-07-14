package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 1448번: 삼각형 만들기
 *
 *	@see https://www.acmicpc.net/problem/1448/
 *
 */
public class Boj1448 {
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] edge = new int[N];

		for (int i = 0; i < N; i++) {
			edge[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(edge);		// 정렬

		int res = -1;
		for (int i = N - 3; i >= 0; i--) {	// 뒤에서부터 인접한 길이를 탐색하며		
			int a = edge[i];
			int b = edge[i + 1];
			int c = edge[i + 2];

			if (c < b + a) {		// 삼각형의 조건에 맞는지 확인
				res = a + b + c;
				break;
			}
		}

		System.out.println(res);	// 삼각형이면 둘레를 아니면 -1 출력
	}
}
