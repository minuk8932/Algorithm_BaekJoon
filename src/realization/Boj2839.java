package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2839 {
	public static final int FIVE = 5;
	public static final int THREE = 3;
	public static final int CANNOT = -1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int sugar = Integer.parseInt(br.readLine());

		if (sugar >= 3) {

			int cnt = 5000 / 3;

			for (int i = 0; i <= sugar / THREE; i++) {
				for (int j = 0; j <= sugar / THREE; j++) {

					if (sugar == (FIVE * i) + (THREE * j)) {
						cnt = Math.min(cnt, i + j);
					}

				}
			}

			if (cnt == 5000 / 3) {
				sb.append(CANNOT);
			} 
			else {
				sb.append(cnt);
			}

			System.out.println(sb.toString());
		}

	}

}
