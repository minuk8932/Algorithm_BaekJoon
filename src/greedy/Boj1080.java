package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1080번: 행렬
 *
 *	@see https://www.acmipc.net/problem/1080/
 *
 */
public class Boj1080 {
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 계산
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		boolean[][] origin = new boolean[N][M];
		boolean[][] after = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();

			for (int j = 0; j < M; j++) {
				origin[i][j] = line.charAt(j) == '1' ? true : false;
			}
		}

		for (int i = 0; i < N; i++) {
			String line = br.readLine();

			for (int j = 0; j < M; j++) {
				after[i][j] = line.charAt(j) == '1' ? true : false;
			}
		}

		int oppo = 0;
		boolean isSame = true;
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 2; j++) {		// 좌측 맨 상단부터, 그리디하게 탐색
				int rangeI = i + 2;
				int rangeJ = j + 2;

				if (origin[i][j] != after[i][j]) {		// 원소하나가 다르면 부분집합 3x3 을 모두 뒤집고 뒤집은 횟수 +1
					oppo++;

					for (int row = i; row <= rangeI; row++) {
						for (int col = j; col <= rangeJ; col++) {
							origin[row][col] = origin[row][col] ? false : true;
						}
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {			// 탐색 완료 후 배열 두개가 다르면 isSame을 거짓으로 변경
			for (int j = 0; j < M; j++) {
				if (origin[i][j] != after[i][j])
					isSame = false;
			}
		}

		System.out.println(isSame ? oppo : -1);		// 결과 값 출력
	}
}
