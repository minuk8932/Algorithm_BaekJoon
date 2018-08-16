package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2448번: 별찍기 - 11
 *
 *	@see https://www.acmicpc.net/problem/2448/
 *
 */
public class Boj2448 {
	private static final String NEW_LINE = "\n";
	private static String space = " ";

	private static String[] uniStar = null;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		br.close();

		uniStar = new String[N];
		uniStar[0] = "  *  ";
		uniStar[1] = " * * ";
		uniStar[2] = "*****";

		System.out.println(shootingStar(N));
	}

	private static String shootingStar(int level) {
		int tmp = level / 3;
		int loop = 0;

		while (tmp >= 2) {
			loop++;
			tmp /= 2;
		}

		for (int i = 1; i < loop + 1; i++) {
			int bot = 3 * (int) Math.pow(2, i);
			int mid = bot / 2;

			space = " ";
			for (int j = mid; j < bot; j++) {
				uniStar[j] = uniStar[j - mid] + space + uniStar[j - mid];
			}

			space = "";
			for (int j = 0; j < mid; j++) {
				space += " ";
			}

			for (int j = 0; j < mid; j++) {
				uniStar[j] = space + uniStar[j] + space;
			}
		}

		for (int i = 0; i < level; i++) sb.append(uniStar[i]).append(NEW_LINE);

		return sb.toString();
	}
}