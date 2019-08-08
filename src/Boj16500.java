import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Boj16500 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] S = br.readLine().toCharArray();		
		int N = Integer.parseInt(br.readLine());
		
		HashSet<String> A = new HashSet<>();
		for(int i = 0; i < N; i++) {
			A.add(br.readLine());
		}
		
		System.out.println(wordPuzzle(S, A));
	}
	
	private static int wordPuzzle(char[] target, HashSet<String> src) {
		
		
		return 0;
	}
}
