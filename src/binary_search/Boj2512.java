package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2512번: 예산
 *
 *	@see https://www.acmicpc.net/problem/2512/
 *
 */
public class Boj2512 {
	private static int N = 0;
	private static int M = 0;
	
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] budget = new int[N];
		int max = 0, sum = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			budget[i] = Integer.parseInt(st.nextToken());
			sum += budget[i];					// 주어진 예산 총합
			max = Math.max(budget[i], max);		// 예산의 최댓값
		}
		
		M = Integer.parseInt(br.readLine());
		int min = M / N;						// 예산의 최솟값
		
		StringBuilder sb = new StringBuilder();
		
		if(sum <= M) {		// 만약 주어진 예산으로 총 예산이 커버가 된다면
			sb.append(max);		// 바로 최댓값을 버퍼에 담음
		}
		else {
			sb.append(binarySearch(min, max, budget));	// 커버하기 힘들다면 이분탐색을 통해 값을 구해 버퍼에 담음
		}
		
		System.out.println(sb.toString());		// 결과값 출력
	}
	
	/**
	 * 이분탐색 메소드
	 * @param from: 범위내 최소 예산
	 * @param to: 범위내 최대 예산
	 * @param b: 각 도시별 예산이 들어있는 배열
	 * @return: 적용 할 수 있는 최대 예산
	 */
	private static int binarySearch(int from, int to, int[] b) {
		int res = 0, left = 0, right = 0;
		
		if(from > to) {		// 최소, 최댓값을 다시 설정
			left = to;
			right = from;
		}
		else {
			left = from;
			right = to;
		}
		
		int mid = 0;
			
		while(left <= right) {			// 이분탐색 시작
			mid = (left + right) / 2;
			int total = 0;

			for(int i = 0; i < N; i++) {
				if(mid >= b[i]) total += b[i];	// 만약 mid 값보다 도시의 예산이 작다면, 도시의 예산을 그대로 더하고
				else	total += mid;			// mid 보다 크면 mid를 더해줌
			}
			
			if(total > M) {			// 위에서 구한 각 도시의 예산 총합이 사용 가능한 총 예산보다 큰 경우
				right = mid - 1;	// 사용 가능 예산보다 작아질때까지 재탐색
			}
			else if(total <= M){		// 도시의 예산 총합이 작거나 같은경우 그때의 mid 값을 계속 가져와 최댓값을 구함
				left = mid + 1;
				res = Math.max(res, mid);
			}
		}
		
		return res;		// 최대 예산 반환
	}
}
