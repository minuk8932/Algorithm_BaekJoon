package implementation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		br.close();

		String[] uniStar = new String[N];
		uniStar[0] = "  *  ";
		uniStar[1] = " * * ";
		uniStar[2] = "*****";
		
		shootingStar(N, uniStar);
	}

	private static void shootingStar(int level, String[] arr) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String space = " ";
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
				arr[j] = arr[j - mid] + space + arr[j - mid];
			}

			space = "";
			for (int j = 0; j < mid; j++) {
				space += " ";
			}

			for (int j = 0; j < mid; j++) {
				arr[j] = space + arr[j] + space;
			}
		}

		for (int i = 0; i < level; i++) {
			bw.write(arr[i]);
			bw.write(NEW_LINE);
		}

		bw.flush();
		bw.close();
	}
}