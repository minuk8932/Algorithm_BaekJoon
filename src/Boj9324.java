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
		
		StringBuilder sbRes = new StringBuilder();
		
		while(T-- > 0){
			StringBuilder sbComp = new StringBuilder();
			String msg = br.readLine();
			char[] words = msg.toCharArray();
			int[] alpha = new int[26];
			
			for(int i = 0; i < words.length; i++){
				alpha[words[i] - ASCII]++;
				sbComp.append(words[i]);
				
				if(alpha[words[i] - ASCII] == 3) {
					if(i == words.length - 1) {
						sbComp.append(words[i]);
						
						break;
					}
					
					if(words[i + 1] != words[i]) {
						sbComp.append(words[i]);
						alpha[words[i] - ASCII] = 0;
					}
				}
			}
			
			System.out.println(sbComp.toString());
			
			sbRes.append(msg.equals(sbComp.toString()) ? OK : NO).append(NEW_LINE);
		}
		
		System.out.println(sbRes.toString());
	}
}
