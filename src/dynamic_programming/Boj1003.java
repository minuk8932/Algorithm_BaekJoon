package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1003 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			int num = Integer.parseInt(br.readLine());
			int[][] chk = new int[2][41];
			
			chk[0][0] = 1;
			chk[1][1] = 1;
			
			for(int i = 2; i < 41; i++){
				chk[0][i] = chk[0][i - 2] + chk[0][i - 1];
				chk[1][i] = chk[1][i - 1] + chk[1][i - 2];
			}

			sb.append(chk[0][num]).append(SPACE).append(chk[1][num]).append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}
}
