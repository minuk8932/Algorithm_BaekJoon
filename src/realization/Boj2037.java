package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2037 {
	private static final String SPACE = " ";
	private static final int CONTENTS = 1_001;

	private static final char[][] KEY = { { '0', '0', '0', '0', '0' }, { '0', ' ', '0', '0', '0' },
			{ '0', 'A', 'B', 'C', '0' }, { '0', 'D', 'E', 'F', '0' }, { '0', 'G', 'H', 'I', '0' },
			{ '0', 'J', 'K', 'L', '0' }, { '0', 'M', 'N', 'O', '0' }, { '0', 'P', 'Q', 'R', 'S' },
			{ '0', 'T', 'U', 'V', '0' }, { '0', 'W', 'X', 'Y', 'Z' } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int p = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		String str = br.readLine().trim();

		int timeToWrite = 0;
		int lengthOfMsg = str.length();

		char[] msg = new char[CONTENTS];
		int[] saveJ = new int[lengthOfMsg + 1];

		for (int i = 1; i < lengthOfMsg + 1; i++) {
			msg[i] = str.charAt(i - 1);
		}

		for (int i = 1; i < lengthOfMsg + 1; i++) {
			for (int j = 1; j < 10; j++) {
				for (int k = 1; k < 5; k++) {
					if (msg[i] == KEY[j][k]) {
						saveJ[i] = j;
					}
				}
			}
		}

		for (int i = 1; i < lengthOfMsg + 1; i++) {
			for (int j = 1; j < 10; j++) {
				for (int k = 1; k < 5; k++) {
					if (msg[i] == KEY[j][k]) {
						if (j != 1) {
							if (saveJ[i - 1] == saveJ[i] || msg[i] == msg[i - 1]) {
								timeToWrite += (p * k) + w;
							} 
							else {
								timeToWrite += (p * k);
							}
						} 
						else {
							timeToWrite += (p * k);
						}
					}
					
				}
			}
		}

		System.out.println(timeToWrite);
	}
}
