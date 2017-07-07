import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author minchoba
 *		yet solved
 */

public class Boj2857 {
	private static final String SPACE = " ";
	private static final String NO_ONE = "HE GOT AWAY!";
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();		
		
		for(int i = 1; i <= 5; i++){
			boolean[] chk = new boolean[26];
			
			for(final char C : br.readLine().toCharArray()){
				if('A' <= C && C <= 'Z')
					chk[C- 65] = true;
			}
			
			if(chk['F' - 65] && chk['B' - 65] && chk['I' - 65]){
				sb.append(i).append(SPACE);
			}
		}
		
		br.close();
		
		System.out.println(sb.length() == 0 ? NO_ONE : sb.toString());
	}

}
