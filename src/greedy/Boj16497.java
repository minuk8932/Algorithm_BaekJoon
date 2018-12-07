package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16497번: 대출 요청
 *
 *	@see https://www.acmicpc.net/problem/16497/
 *
 */
public class Boj16497 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Pair[] loan = new Pair[2 * N];
		Arrays.fill(loan, new Pair(0, 0));

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			loan[i * 2] = new Pair(from, 1);
			loan[i * 2 + 1] = new Pair(to, -1);
		}

		Arrays.sort(loan);
		
		int K = Integer.parseInt(br.readLine());

		System.out.println(requestAvailable(loan, K, N));
	}

	private static class Pair implements Comparable<Pair>{
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			if (this.x < p.x) return -1;
			else if (this.x > p.x) return 1;
			else return 0;
		}
	}

	private static int requestAvailable(Pair[] arr, int k, int n) {
		int tmp = 0;
		
		for (int i = 0; i < 2 * n; i++) {		// 기간별 정리 후, 대출 가능 여부 계산
			tmp += arr[i].y;
			
			if (tmp > k) {
				return 0;
			}
		}
		
		return 1;
	}
}
