package array;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Boj1181 {
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		Word[] word = new Word[N];

		for (int i = 0; i < N; i++) {
			word[i] = new Word(br.readLine());
		}
		Arrays.sort(word, Word.comparator);

		for (int i = 0; i < N; i++) {
			if (i < N - 1 && !word[i].voca.equals(word[i + 1].voca)) {
				sb.append(word[i].voca).append(NEW_LINE);
			}
		}
		
		sb.append(word[N - 1].voca);
		
		System.out.println(sb.toString());
	}

	private static class Word {
		public String voca;

		private Word(String voca) {
			this.voca = voca;
		}

		private static Comparator<Word> comparator = new Comparator<Word>() {

			@Override
			public int compare(Word w1, Word w2) {

				if (w1.voca.length() < w2.voca.length()) {
					return -1;
				} else if (w1.voca.length() == w2.voca.length()) {
					return w1.voca.compareTo(w2.voca);
				} else {
					return 1;
				}

			}
		};
	}
}
