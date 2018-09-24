package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1333번: 부재중 전화
 *
 *	@see https://www.acmicpc.net/problem/1333/
 *
 */
public class Boj1333 {
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		int res = 0;
		int runningTime = L + 5;				// 한 곡 실행 시간 + 대기시간
		int playTime = N * runningTime - 5;		// 앨범 전체 실행 시간
		
LOOP:	for (int i = 0; i < N; i++) {			// 곡의 수 만큼
			int loop = (i + 1) * runningTime;
			int start = i * runningTime + L;
	
			for (int j = start; j < loop; j++) {	// 들을 수 있는 구간 설정 (격 단위)
				if (j % D == 0) {								// 이 때의 j 값이 D의 배수인 경우
					res = j;									// 그때의 시간을 저장하고 전체 반복문 종료
					break LOOP;
				}
			}
		}

		// 전화를 앨범 끝까지 못받는 경우, 앨범 종료 후 다음 전화가 울리는 시간을 받는 경우엔 그 시간을 출력
		System.out.println(res == 0 ? playTime + D - (playTime % D) : res);
	}
}
