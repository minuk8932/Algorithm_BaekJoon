package heap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2014번: 소수들의 곱
 *
 *	@see https://www.acmicpc.net/problem/2014/
 *
 */
public class Boj2014 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		long[] primes = new long[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			primes[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(getValue(K, N, primes));
	}

	private static long getValue(int k, int n, long[] arr) {
		PriorityQueue<Long> pq = new PriorityQueue<>();

		for (int i = 0; i < k; i++) {
			pq.offer(arr[i]);
		}

		for (int i = 0; i < n - 1; i++) {			// n - 1번째 까지
			long current = pq.poll();

			for (int j = 0; j < k; j++) {			// 곱 연산 처리해서 우선순위큐에 담아줌
				pq.add(current * arr[j]);

				if (current % arr[j] == 0) break;	// 중복 제거
			}
		}

		return pq.poll();
	}
}
