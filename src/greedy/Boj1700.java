package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1700번: 멀티탭 스케줄링
 *
 *	@see https://www.acmicpc.net/problem/1700/
 *
 */
public class Boj1700 {
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] con = new int[N];
		int[] thg = new int[K];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			thg[i] = Integer.parseInt(st.nextToken());
		}

		// 스케줄링
		int poll = 0; // 뽑는 횟수

		for (int insert = 0; insert < K; insert++) {
			boolean isIn = false, wasEmpty = false;

			for (int inserted = 0; inserted < N; inserted++) { // 이미 꽂혀있는 제품인지 확인
				if (thg[insert] == con[inserted]) {
					isIn = true;
					break;
				}
			}
			if (isIn) continue; // 이미 꽂혀있는 거라면 다음 기기로 넘어감

			for (int inserted = 0; inserted < N; inserted++) { // 빈 구멍이 있는지 확인
				if (con[inserted] == 0) {
					con[inserted] = thg[insert];
					wasEmpty = true;
					break;
				}
			}
			if (wasEmpty) continue; 	// 비어있는 구멍에 꽂았으니 다음 물건으로 넘어감

			int diff = -1, newConIdx = 0;
			for (int inserted = 0; inserted < N; inserted++) { // 가장 마지막에 사용될 또는 아예 사용되지 않을 제품을 찾음
				int tmp = 0;

				for (int after = insert + 1; after < K && con[inserted] != thg[after]; after++) { // 현재 다음으로 들어올 제품부터
					tmp++; 							// 이미 꽂혀있는것과 다음으로 들어온 제품들이 다른 경우, 임시 간격을 구하고
				}

				if (tmp > diff) { 			// 즉 꽂혀있는 것 중, 가장 늦게 들어오는 제품을 찾음
					newConIdx = inserted; 
					diff = tmp;
				}
			}

			poll++; // 콘센트 뽑는 횟수 증가
			con[newConIdx] = thg[insert]; // 뽑아야 할 제품을 뽑고 새로운 제품을 꽂음
		}

		System.out.println(poll);		// 결과값 출력
	}
}
