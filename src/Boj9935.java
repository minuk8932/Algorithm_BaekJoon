
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *		100%에서 런타임
 *
 */
public class Boj9935 {
	private static final String EMPTY = "FRULA";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] inputChars = br.readLine().toCharArray();
		char[] boomChars = br.readLine().toCharArray();
		
		char[] stack = new char[inputChars.length];
		int tos = -1;
		
		for(final char C : inputChars){
			stack[++tos] = C;
			
			boolean isBoomed = true;
			
			for(int i = 0; i < boomChars.length; i++){
				if(boomChars[boomChars.length - 1 - i] != stack[tos - i]){
					isBoomed = false;
					
					break;
				}
			}
			if(isBoomed){
				tos -= boomChars.length;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < tos + 1; i++){
			sb.append(stack[i]);
		}
		
		System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
	}
}
