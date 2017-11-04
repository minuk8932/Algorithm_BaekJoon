package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2033 {
	public static void main(String args[]) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		br.close();

		int comp = 10;

		while (N > comp) {
			int ModComp = N % comp;

			if (ModComp * 10 / comp >= 5) {
				N += comp;
			}

			N -= ModComp;
			comp *= 10;
		}

		System.out.println(N);
	}
}
