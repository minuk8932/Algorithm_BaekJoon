package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj5618 {
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);

		if (N == 3) {
			for (int i = 1; i <= A[0]; i++) {
				if (A[0] % i == 0 && A[1] % i == 0 && A[2] % i == 0) {
					sb.append(i).append(NEW_LINE);
				}
			}
		}
		else {
			for (int i = 1; i <= A[0]; i++) {
				if (A[0] % i == 0 && A[1] % i == 0) {
					sb.append(i).append(NEW_LINE);
				}
			}
		}

		System.out.println(sb.toString());

	}

}
