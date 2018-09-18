package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2805번: 나무 자르기
 *
 *	@see https://www.acmicpc.net/problem/2805/
 *
 */
public class Boj2805 {
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] tree = new int[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++)	tree[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(tree);		// 크기 순 정렬

		int left = 1;
		int right = tree[N - 1];	// 가장 큰 값을 오른쪽에 저장
		int mid = 0;
		long leng = 0;
		
		if(N == 1) {					// 나무가 1개인 경우
			right = (int) (tree[0] - M);
		}
		else {
			while (left <= right) {				// 이분 탐색 실행
				mid = (left + right) / 2;
				leng = getLength(tree, N, mid);		// mid값에 의해 잘린 나무의 길이를 저장
	
				if (leng >= M) {			// 나온 길이가 가져가려는 나무보다 큰 경우
					left = mid + 1;
				} 
				else  {						// 나온 길이가 가져가려는 나무보다 작거나 같은 경우
					right = mid - 1;
				}
			}
		}

		System.out.println(right);			// 결과 값 출력
	}
	
	/**
	 * 현재 나무 길이 획득 메소드
	 * 
	 */
	private static long getLength(int[] arr, int n, int middle) {
		long value = 0;

		for (int i = 0; i < n; i++) {
			if (arr[i] <= middle) continue;		// 자른 나무의 길이가 0 이하인 경우

			value += (arr[i] - middle);
		}

		return value;
	}
}
