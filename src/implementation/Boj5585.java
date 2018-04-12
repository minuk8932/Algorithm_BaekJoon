package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj5585 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int c1 = 0, c5 = 0, c10 = 0, c50 = 0, c100 = 0, c500 = 0;

		N = 1000 - N;

		for (; N > 0;) {
			if (N >= 500) {
				c500++;
				N = N - 500;
			} else if (N >= 100) {
				c100++;
				N = N - 100;
			} else if (N >= 50) {
				c50++;
				N = N - 50;
			} else if (N >= 10) {
				c10++;
				N = N - 10;
			} else if (N >= 5) {
				c5++;
				N = N - 5;
			} else if (N >= 1) {
				c1++;
				N = N - 1;
			}
		}

		System.out.println(c500 + c100 + c50 + c10 + c5 + c1);

	}

}
