package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj5554 {
	public static final int MAX_TIME = 3600;
	public static final int MIN_TIME = 60;
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int M = 0, S = 0;

		for (int i = 0; i < 4; i++) {
			S += Integer.parseInt(br.readLine());
		}

		for (; S >= 60;) {
			if (60 <= S && S < 3600) {
				S -= 60;
				M += 1;
			}
		}
		sb.append(M).append(NEW_LINE);
		sb.append(S);

		System.out.println(sb.toString());
	}

}
