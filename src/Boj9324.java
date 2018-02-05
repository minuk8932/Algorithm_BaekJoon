import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9324 {
	private static final int ASCII = 65;
	private static final int MAX = 100_001;

	private static final String NEW_LINE = "\n";
	private static final String OK = "OK";
	private static final String NO = "FAKE";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		char[] msg = new char[MAX];

		while (N-- > 0) {
			msg = br.readLine().toCharArray();

			int[] alphaChk = new int[30];
			boolean chk = false;

			for (int i = 0; i < msg.length; i++) {
				alphaChk[msg[i] - ASCII]++;

				if (alphaChk[msg[i] - ASCII] == 3) {
					alphaChk[msg[i] - ASCII] = 0;
					i++;

					if (msg[i] != msg[i - 1]) {
						chk = true;
						break;
					}
				}

			}

			if (!chk) {
				sb.append(OK).append(NEW_LINE);
			} 
			else {
				sb.append(NO).append(NEW_LINE);
			}
			System.out.println();
		}

		System.out.println(sb.toString());
	}
}
