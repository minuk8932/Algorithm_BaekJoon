package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj3460 {
	public static final String SPACE = " ";
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {

			int bi = Integer.parseInt(br.readLine());
			char[] binary = Integer.toBinaryString(bi).toCharArray();

			for (int i = binary.length - 1; i >= 0; i--) {
				if (binary[i] == '1') {
					sb.append((binary.length-1) - i).append(SPACE);
				}
			}
			sb.append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}

}
