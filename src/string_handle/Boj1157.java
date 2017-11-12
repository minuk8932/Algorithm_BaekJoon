package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.format.ResolverStyle;
import java.util.Arrays;

public class Boj1157 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		char[] word = line.toUpperCase().toCharArray();

		int[] alpha = new int[26];
		int[] tmp = new int[26];
		int max = 0, cnt = 0, idx = 0;

		for (int i = 0; i < word.length; i++) {
			alpha[(int) (word[i]) - 65]++;
			tmp[(int) (word[i]) - 65] = alpha[(int) (word[i]) - 65];
		}

		Arrays.sort(tmp);

		for (int i = 0; i < alpha.length; i++) {
				max = Math.max(tmp[i], max);			
		}

		for (int i = 0; i < alpha.length; i++) {
			if (alpha[i] != 0) {
				if (max == alpha[i]) {
					cnt++;
					idx = i;
				}
			}
		}
		

		if (cnt == 1) {
			System.out.println((char)(idx + 65));
		} else {
			System.out.println("?");
		}
	}
}
