package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj2721 {
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while (T > 0) {
			int N = Integer.parseInt(br.readLine());
			int sum = 0, total = 0;
			
			for (int i = 1; i <= N; i++) {		
				sum = 0;
				for (int j = 1; j <= i+1; j++) {
					sum += j;
				}
				total += sum * i;
			}
			sb.append(total).append(NEW_LINE);
			T--;
		}

		System.out.println(sb.toString());
	}

}
