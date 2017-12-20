import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2789 {
	private static final char[] UNIV = {'C', 'A', 'M', 'B', 'R', 'I', 'D', 'G', 'E'};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String words = br.readLine();
		char[] censor = words.toCharArray();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < censor.length; i++){
			for(int j = 0 ; j < UNIV.length; j++){
				if(censor[i] == UNIV[j]){
					censor[i] = ' ';
				}
			}
		}
		for(int i = 0; i < censor.length; i++){
			if(censor[i] != ' '){
				sb.append(censor[i]);
			}
		}
		
		System.out.println(sb.toString());
	}
}
