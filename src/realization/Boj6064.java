package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj6064 {
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while (T-- != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if (M > N) {
				int tmp = M;
				M = N;
				N = tmp;
				tmp = x;
				x = y;
				y = tmp;
			}

			int diff = N - M;
			int cnt = 0;
			int current = x;

			while (current != y) {
				cnt++;

				if (current > diff) {
					current -= diff;
				}

				else {
					current = N - (diff - current);
				}

				if (current == x) {
					break;
				}
			}

			if (cnt != 0 && current == x) {
				sb.append(-1).append(NEW_LINE);
			}

			else {
				sb.append(x + cnt * M).append(NEW_LINE);
			}
		}

		br.close();
		System.out.println(sb.toString());
	}
}
