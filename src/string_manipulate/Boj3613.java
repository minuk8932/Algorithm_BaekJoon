package string_manipulate;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author minchoba
 *	
 */

public class Boj3613 {
	private static final String NOTHING = "Error!";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] chars = br.readLine().toCharArray();
		br.close();

		// error 1: start with under bar
		if(chars[0] == '_'){
			System.out.println(NOTHING);
		}
		// error 2: end with under bar
		else if(chars[chars.length - 1] == '_'){
			System.out.println(NOTHING);
		}
		// error 3: start with Upper case
		else if('A' <= chars[0] && chars[0] <= 'Z'){
			System.out.println(NOTHING);
		}
		
		else{
			boolean isJava = true;
			boolean isCPP = true;
			
			for(int i = 0; i < chars.length; i++){
				if(chars[i] == '_'){
					isJava = false;
					
					// error 4: under bar consequently appeared
					if( i > 0 && chars[i -1] == '_'){
						System.out.println(NOTHING);
						return;
					}
				}
				else if('A' <= chars[i] && chars[i] <= 'Z'){
					isCPP = false;
				}
			}
			
			//error 5: two methods are used 
			if(!isJava && !isCPP){
				System.out.println(NOTHING);
			}
			else if(isJava && isCPP){
				System.out.println(new String(chars));				
			}
			else if(isJava){
				StringBuilder sb = new StringBuilder();
				
				for(final char C : chars){
					if('A' <= C &&  C <= 'Z'){
						sb.append("_").append((char)(C + 32));
					}
					else{
						sb.append(C);
					}
				}
				System.out.println(sb.toString());
			}
			else{
				StringBuilder sb = new StringBuilder();
				
				for(int i = 0; i < chars.length; i++){
					if(chars[i] == '_'){
						sb.append((char) (chars[++i] - 32));
					}
					else{
						sb.append(chars[i]);
					}
				}
				System.out.println(sb.toString());
			}
		}
		
	}

}
