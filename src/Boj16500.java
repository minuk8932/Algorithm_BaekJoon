import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj16500 {
	private static final String SPACE = " ";
	private static final String EMPTY = "";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		int N = Integer.parseInt(br.readLine());
		
		String[] A = new String[N];
		for(int i = 0; i < N; i++) {
			A[i] = br.readLine();
		}
		
		System.out.println(wordPuzzle(S, A, N) ? 1 : 0);
	}
	
	private static boolean wordPuzzle(String S, String[] A, int N) {
		for(int i = 0; i < A.length; i++) {
			S = S.replace(A[i], SPACE);
		}
		
		S = S.replaceAll(SPACE, EMPTY);
		return S.equals(EMPTY) ? true : false;
	}
}
