import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj9935 {
	private static final String EMPTY = "FRULA";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] inputChars = br.readLine().toCharArray();
		char[] boomChars = br.readLine().toCharArray();
		
		Stack<Character> input = new Stack<>();
		
		for(char word : inputChars){
			input.add(word);
		}
	}
}
