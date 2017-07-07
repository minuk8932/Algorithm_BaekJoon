import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj1764 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int N = Integer.parseInt(st.nextToken()); // didn't hear
		int M = Integer.parseInt(st.nextToken()); // didn't see

		
		String[] all = new String[N + M];

		for (int i = 0; i < all.length; i++) {
			all[i] = br.readLine();
		}

		ArrayList<String> unSH = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			if (all[i].charAt(0) == all[i + N].charAt(0)) {
				if (all[i].equals(all[i + N])) {
					unSH.add(all[i]);
				}
			}
		}

		int size = unSH.size();

		Collections.sort(unSH);

		StringBuilder sb = new StringBuilder();
		sb.append(size).append(NEW_LINE);

		for (int i = 0; i < size; i++) {
			sb.append(unSH.get(i)).append(NEW_LINE);
		}
		System.out.println(sb.toString());

	}
}
