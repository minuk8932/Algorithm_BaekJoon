package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10819번: 차이를 최대로
 *
 *	@see https://www.acmicpc.net/problem/10819/
 *
 */
public class Boj10819 {
	private static final int SIZE = 10;
	private static final int INF = 1_000;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[SIZE];
		int[] tmpBig = new int[SIZE];
		int[] tmpSmall = new int[SIZE];
		
		Arrays.fill(arr, INF);

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int loop = 0;
		
		if (N % 2 == 0) loop = N / 2;
		else loop = N / 2 + 1;

		int idx = 1;
		
		Arrays.sort(arr);
		for (int i = 0; i < loop; i++) {
			tmpBig[idx] = arr[i];
			tmpBig[idx + 1] = arr[N - 1 - i];	// 가장 작은 값을 맨앞으로
			
			tmpSmall[idx] = arr[N - 1 - i];
			tmpSmall[idx + 1] = arr[i];			// 가장 큰 값을 맨 앞으로
												// 각각 크고 작은 순으로 배치해줌
			idx += 2;
		}
		
		tmpBig[0] = tmpBig[N];		// 맨뒤의 값을 가장 앞으로 빼줌
		tmpBig[N] = 0;
		
		tmpSmall[0] = tmpSmall[N];
		tmpSmall[N] = 0;
	
		int max = Math.max(getSum(tmpBig, N - 1), getSum(tmpSmall, N - 1));	// 두 배치 중 큰 값을 최대에 담고

		System.out.println(max);		// 결과값 출력
	}
	
	/**
	 * 
	 * @param arr 연산 할 배열
	 * @param loop N - 2 까지 계산
	 * @return	그때의 최대 합
	 */
	private static int getSum(int[] arr, int loop) {
		int sum = 0;
		for(int i = 0; i < loop; i++) {
			int A = arr[i] - arr[i + 1];
			if(A < 0) A = -A;			// A가 음수면 양수로 바꿔줌
			
			sum += A;
		}
		
		return sum;
	}
}
