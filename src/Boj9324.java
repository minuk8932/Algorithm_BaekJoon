import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9324 {
	private static final int ASCII = 65;

	private static final String NEW_LINE = "\n";
	private static final String OK = "OK";
	private static final String NO = "FAKE";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			char[] words = br.readLine().toCharArray();
			int[] alpha = new int[26];
			boolean isTrue = true;
			
			for(int i = 0; i < words.length; i++){
				alpha[words[i] - ASCII]++;
				
				if(alpha[words[i] - ASCII] == 3){
					alpha[words[i] - ASCII] = 0;
					
					if(i == words.length - 1){
						isTrue = false;
						break;
					}
					
					if(words[i] != words[i + 1]){
						isTrue = false;
						break;
					}
					
					i++;
				}
			}
			
			sb.append(isTrue ? OK : NO).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}
