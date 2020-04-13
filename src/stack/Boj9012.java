package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 9012번: 괄호
 *
 * @see https://www.acmicpc.net/problem/9012/
 *
 */
public class Boj9012 {
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			sb.append(judgement(br.readLine().toCharArray()) ? "YES": "NO").append(NEW_LINE);
		}

		System.out.println(sb.toString());
	}

	private static boolean judgement(char[] div) {
		int V = 0;

		for (int i = 0; i < div.length; i++) {
			if(V < 0) return false;					// not proper

			if (div[i] == '(') V++;
			else V--;
		}

		return V == 0;
	}
}
