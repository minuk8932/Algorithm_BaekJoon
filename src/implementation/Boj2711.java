package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj2711 {
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		while (N > 0) {
			ArrayList<Character> words = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int idx = Integer.parseInt(st.nextToken()) - 1;
			String line = st.nextToken();
			int length = line.length();
			char[] alpha = new char[length];
			alpha = line.toCharArray();

			for (int i = 0; i < length; i++) {
				words.add(alpha[i]);
			}
			words.remove(idx);
			
			for (int i = 0; i < (length - 1); i++) {	
				sb.append(words.get(i));
			}
			sb.append(NEW_LINE);
			
			N--;
		}
		System.out.println(sb.toString());
	}

}
