package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9322번: 철벽 보안 알고리즘
 *
 *	@see https://www.acmicpc.net/problem/9322/
 *
 */
public class Boj9322 {
	private static final char SPACE = ' ';
	private static final char NEW_LINE = '\n';

	private static HashMap<String, Integer> publicKey1;
	private static String[] publicKey2;
	private static String[] pw;

	private static int n;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			n = Integer.parseInt(br.readLine());
			init();

			publicKeySetter(br.readLine());
			publicKeyRearranged(br.readLine());
			passwordInput(br.readLine());

			sb.append(decoding()).append(NEW_LINE);
		}

		System.out.println(sb);
	}

	private static void passwordInput(String input) {
		StringTokenizer st = new StringTokenizer(input);
		for(int i = 0; i < n; i++) {
			pw[i] = st.nextToken();
		}
	}

	private static void publicKeySetter(String input) {
		StringTokenizer st = new StringTokenizer(input);
		for(int i = 0; i < n; i++) {
			publicKey1.put(st.nextToken(), i);
		}
	}

	private static void publicKeyRearranged(String input) {
		StringTokenizer st = new StringTokenizer(input);
		for(int i = 0; i < n; i++) {
			publicKey2[i] = st.nextToken();
		}
	}

	private static void init() {
		publicKey1  = new HashMap<>();
		publicKey2  = new String[n];
		pw = new String[n];
	}

	private static StringBuilder decoding() {
		String[] res = new String[publicKey2.length];

		for(int i = 0; i < publicKey2.length; i++) {
			int idx = publicKey1.get(publicKey2[i]);
			res[idx] = pw[i];
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < res.length; i++) {
			sb.append(res[i]).append(SPACE);
		}

		return sb;
	}
}
