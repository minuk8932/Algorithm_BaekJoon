package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1654번: 랜선 자르기
 *
 *	@see https://www.acmicpc.net/problem/1654/
 *
 */
public class Boj1654 {
	private static int N = 0;
	private static int K = 0;
	
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		long[] lan = new long[K];
		long max = 0, min = 0;
		
		for(int i = 0; i < K; i++) {
			lan[i] = Long.parseLong(br.readLine());
			max = Math.max(lan[i], max);			// 범위내에서 값을 구하기위한 최댓값
		}		
		
		System.out.println(binarySearch(min, max, lan));		// 이분 탐색을 이용한 결과값 출력
	}
	
	/**
	 * 이분 탐색 메소드
	 * @param from: 최솟값의 범위
	 * @param to: 최댓값의 범위
	 * @param l: 랜선의 길이 저장
	 * @return: N값을 구할 수 있는 최대 길이
	 */
	private static long binarySearch(long from, long to, long[] l) {
		long res = 0, left = from, right = to;
		long mid = 0;
			
		while(left <= right) {
			mid = (left + right) / 2;
			long total = 0;
			
			if(mid != 0) {						// mid가 0이 아닌경우에만
				for(int i = 0; i < K; i++) {
					long tmp = l[i] / mid;		// 랜선길이를 나눈 몫을 구해서
					total += tmp;				// 총합을 구함
				}
			}
			
			if(total >= N) {				// 총합이 N보다 같거나 큰 경우, 나누는 값 자체는 작으므로
				left = mid + 1;				// 왼쪽 피벗을 중앙으로 이동시키고
				res = Math.max(res, mid);	// 가능한 랜선의 길이 중 최댓값을 저장
			}
			else if(total < N){		// 총합이 구하려는 경우보다 작은경우 
				right = mid - 1;	// 나누는 값이 구하려는 값 보다 큰 경우이므로, 오른쪽 피벗을 중앙으로
			}
		}
		
		return res = res == 0 ? 1 : res;		// mid 값이 0인 경우 res = 1 그 외엔 그대로 값 반환
	}
}
