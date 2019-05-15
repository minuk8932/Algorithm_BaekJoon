package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj4072 {
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	private static final String TERMINATE = "#";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			if(line.equals(TERMINATE)) break;
			
			StringTokenizer st = new StringTokenizer(line);
			while(st.hasMoreTokens()) {
				char[] word = st.nextToken().toCharArray();
				
				for(int i = word.length - 1; i >= 0; i--) {		// 역순 나열
					sb.append(word[i]);
				}
				
				sb.append(SPACE);
			}
			
			sb.append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
}
