import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1700 {
	private static final int INF = 101;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] con = new int[N];
		int[] thg = new int[K];
		boolean[] isIn = new boolean[INF];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			thg[i] = Integer.parseInt(st.nextToken());
		}

		int poll = 0;
		int push = 1;

		con[0] = thg[0];
		isIn[thg[0]] = true;

		for (int i = 1; i < K; i++) {
			for (int j = 1; j < N; j++) {
				if (con[j - 1] != thg[i]) {
					con[j] = thg[i];
					isIn[thg[i]] = true;
				}
			}

			push++;
		}

		for (int i = push; i < K; i++) {
			int cnt = 0;

			for (int j = 0; j < N; j++) {

				if (!isIn[thg[i]]) {
					if (con[j] != thg[i]) {
						isIn[thg[i]] = true;
						poll++;
					}
				}
				else {
					
				}
				cnt++;
			}

			if (poll == N) {

			}
		}

		System.out.println(poll);
	}
}
