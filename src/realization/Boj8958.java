package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj8958 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testNum, sum = 0, score = 0;

		testNum = Integer.parseInt(br.readLine());

		char[][] check = new char[testNum][80];

		Arrays.fill(check, null);

		for (int i = 0; i < testNum; i++) {
			String str = br.readLine();
			check[i] = str.toCharArray();

			for (int j = 0; j < check[i].length; j++) {
				switch (check[i][j]) {
				case 'O':
					score++;
					sum += score;

					break;

				case 'X':
					score = 0;

					break;

				default:
					break;
				}

			}
			System.out.println(sum);
			sum = 0;
			score = 0;
		}
		

		br.close();
	}

}
