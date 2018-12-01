import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj16500 {
	private static final String SPACE = " ";
	private static final String EMPTY = "";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		int N = Integer.parseInt(br.readLine());
		
		Word[] A = new Word[N];
		for(int i = 0; i < N; i++) {
			A[i] = new Word(br.readLine());
		}
		
		Arrays.sort(A);
		System.out.println(wordPuzzle(S, A, N) ? 1 : 0);
	}
	
	private static boolean wordPuzzle(String S, Word[] A, int N) {
		for(int i = 0; i < A.length; i++) {
			S = S.replace(A[i].w, SPACE);
		}
		
		S = S.replaceAll(SPACE, EMPTY);
		return S.equals(EMPTY) ? true : false;
	}
	
	private static class Word implements Comparable<Word>{
		String w;
		
		public Word(String w) {
			this.w = w;
		}

		@Override
		public int compareTo(Word w) {
			return this.w.length() > w.w.length() ? -1 : 1;
		}
	}
}
